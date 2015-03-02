package school.students.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import school.students.model.Philosopher;
import school.students.repository.StudentRepository;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class StudentsController {

    @Autowired
    private StudentRepository studentRepository;

    @RequestMapping("/")
    @ResponseBody
    public String home() {
        String content = StreamSupport.stream(studentRepository.findAll().spliterator(), false)
                .map((student) -> {
                    String philosophers = student.getPhilosophers().stream()
                            .map(Philosopher::getName)
                            .collect(Collectors.joining(", "));
                    return student.toString() + ": " + philosophers;
                })
                .collect(Collectors.joining(" \n"));
        return "<pre>" + content + "</pre>";
    }
}
