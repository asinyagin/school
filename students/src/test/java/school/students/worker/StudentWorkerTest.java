package school.students.worker;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;
import school.students.model.Philosopher;
import school.students.model.Student;
import school.students.rpc.Philosophers;
import school.students.service.PhilosopherService;
import school.students.service.QuestionService;
import school.students.service.StudentService;

import java.util.ArrayList;

public class StudentWorkerTest {

    /**
     * StudentWorker can get a philosopher of a student that already have a philosopher.
     */
    @Test
    public void getsPhilosopher() throws Exception {
        Student student = new Student();
        student.getPhilosophers().add(new Philosopher(student, "John", true));
        Philosopher philosopher = new Philosopher(student, "Jack", false);
        student.getPhilosophers().add(philosopher);
        QuestionService questionService = Mockito.mock(QuestionService.class);
        Mockito.when(questionService.findAll()).thenReturn(new ArrayList<>());
        StudentWorker worker = new StudentWorker(
                student,
                Mockito.mock(Philosophers.class),
                Mockito.mock(StudentService.class),
                Mockito.mock(PhilosopherService.class),
                questionService
        );
        MatcherAssert.assertThat(worker.getPhilosopher(), Matchers.equalTo(philosopher));
    }
}
