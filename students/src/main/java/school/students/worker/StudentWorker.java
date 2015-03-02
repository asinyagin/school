package school.students.worker;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import school.students.model.Philosopher;
import school.students.model.Question;
import school.students.model.Student;
import school.students.rpc.Philosophers;
import school.students.service.PhilosopherService;
import school.students.service.QuestionService;
import school.students.service.StudentService;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

// TODO: StudentWorker and Student are not thread-safe.
public class StudentWorker implements Runnable {

    private static final Log log = LogFactory.getLog(StudentWorker.class);

    private Student student;

    private final StudentService studentService;

    private final PhilosopherService philosopherService;

    private final QuestionService questionService;

    private final Philosophers philosophers;

    private final ScheduledExecutorService service =
            Executors.newSingleThreadScheduledExecutor();

    private final Lock lock = new ReentrantLock(true);

    private final Map<Long, Set<String>> answers;

    public StudentWorker(
            Student student,
            Philosophers philosophers,
            StudentService studentService,
            PhilosopherService philosopherService,
            QuestionService questionService
    ) {
        this.student = student;
        this.philosophers = philosophers;
        this.studentService = studentService;
        this.philosopherService = philosopherService;
        this.questionService = questionService;
        answers = StreamSupport.stream(questionService.findAll().spliterator(), false)
                .collect(Collectors.toMap(Question::getId, question -> new HashSet<>()));
    }

    public void start() {
        service.scheduleAtFixedRate(this, 0, 100, TimeUnit.MILLISECONDS);
    }

    public void stop() throws Exception {
        service.shutdown();
    }

    @Override
    public void run() {
        try {
            if (lock.tryLock(100, TimeUnit.MILLISECONDS)) {
                //log.info(student.toString() + " - Working");
                Philosopher philosopher = getPhilosopher();
                Question question = questionService.randomQuestion();
                boolean check = askQuestionAndCheck(philosopher, question);
                if (check) {
                    philosophers.FIRE_PHIL(philosopher.getName(), student.getId());
                    philosopher.setFired(true);
                    philosopher.setQuestion(question);
                    philosopher.setAnswers(answers.get(question.getId()));
                    philosopherService.save(philosopher);
                    if (student.getPhilosophers().size() == 3) {
                        log.info(student.toString() + " - Stop");
                        stop();
                    }
                }
            } else {
                stop();
                throw new Exception("Could not run student worker after 100 ms of waiting.");
            }
        } catch (Exception e) {
            log.error(e);
        }
    }

    public Philosopher getPhilosopher() throws Exception {
        Philosopher philosopher = currentPhilosopher();
        if (philosopher == null) {
            Set<String> names = student.getPhilosophers().stream()
                    .map(Philosopher::getName)
                    .collect(Collectors.toSet());
            String name = philosophers.GET_PHIL(student.getId());
            if (names.contains(name)) {
                throw new Exception(String.format("Got fired philosopher %s", name));
            }
            student.getPhilosophers().add(new Philosopher(student, name));
            student = studentService.save(student);
            philosopher = currentPhilosopher();
        }
        return philosopher;
    }

    private Philosopher currentPhilosopher() {
        return student.getPhilosophers().stream()
                .filter((philosopher) -> !philosopher.isFired())
                .findFirst().orElse(null);
    }

    public boolean askQuestionAndCheck(Philosopher philosopher, Question question) {
        String answer = philosophers.ASK_PHIL(
                philosopher.getName(), question.getText()
        );

        Set<String> questionAnswers = answers.get(question.getId());
        questionAnswers.add(answer);

        return questionAnswers.size() == 3;
    }
}
