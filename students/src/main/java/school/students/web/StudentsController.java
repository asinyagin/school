package school.students.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.students.model.Student;
import school.students.repository.StudentRepository;

@RestController
@RequestMapping("/students")
public class StudentsController {

    @Autowired
    private StudentRepository studentRepository;

    @RequestMapping("/")
    public Iterable<Student> list() {
        return studentRepository.findAll();
    }
}
