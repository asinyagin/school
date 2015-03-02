package school.students.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import school.students.service.StudentService;

import java.util.Random;

@EnableScheduling
@Component
public class CreateStudentTask {

    @Autowired
    public StudentService studentService;

    private Random random = new Random(System.currentTimeMillis());

    @Scheduled(fixedRate = 1000)
    public void createStudent() {
        if (random.nextInt(10) < 3) {
            studentService.createStudent();
        }
    }
}
