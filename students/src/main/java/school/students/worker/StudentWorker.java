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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
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

    // TODO: We store answer's hash code here, so there is a small probability of collision.
    private final Map<Long, Map<Integer, Integer>> answers;

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
                .collect(Collectors.toMap(Question::getId, question -> new HashMap<>()));
    }

    public void start() {
        service.scheduleAtFixedRate(this, 0, 100, TimeUnit.MILLISECONDS);
    }

    public void stop() throws Exception {
        service.shutdown();
    }

    @Override
    public void run() {
        //log.info(student.toString() + " - Working");
        try {
            Philosopher philosopher = getPhilosopher();
            boolean check = askQuestionAndCheck(philosopher, questionService.randomQuestion());
            if (check) {
                philosopher.setFired(true);
                philosopherService.save(philosopher);
                if (student.getPhilosophers().size() == 3) {
                    log.info(student.toString() + " - Stop");
                    this.stop();
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
    }

    private Philosopher getPhilosopher() throws Exception {
        Philosopher philosopher = currentPhilosopher();
        if (philosopher == null) {
            Set<String> names = student.getPhilosophers().stream()
                    .map(Philosopher::getName)
                    .collect(Collectors.toSet());
            String name = "";
            int attempts = 5;
            while (attempts-- > 0) {
                name = philosophers.GET_PHIL();
                if (!names.contains(name)) break;
            }
            if (attempts == 0) {
                throw new Exception(String.format(
                        "Could not get philosopher name with %s attempts.", attempts
                ));
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

    private boolean askQuestionAndCheck(Philosopher philosopher, Question question) {
        Integer answer = philosophers.ASK_PHIL(
                philosopher.getName(), question.getText()
        ).hashCode();

        Map<Integer, Integer> questionAnswers = answers.get(question.getId());
        Integer repeats = questionAnswers.get(answer);
        if (repeats == null) repeats = 0;
        questionAnswers.put(answer, ++repeats);

        return repeats == 3;
    }
}