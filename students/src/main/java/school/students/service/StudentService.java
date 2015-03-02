package school.students.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.students.model.Student;
import school.students.repository.StudentRepository;
import school.students.rpc.Philosophers;
import school.students.worker.StudentWorker;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PhilosopherService philosopherService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private Philosophers philosophers;

    public void createStudent() {
        Student student = studentRepository.save(new Student());
        new StudentWorker(student, philosophers, this, philosopherService, questionService).start();
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }
}
