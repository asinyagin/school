package school.philosophers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.philosophers.model.Philosopher;
import school.philosophers.repository.PhilosopherRepository;

@RestController
@RequestMapping("/philosophers")
public class PhilosophersController {

    @Autowired
    private PhilosopherRepository philosopherRepository;

    @RequestMapping("/")
    public Iterable<Philosopher> list() {
        return philosopherRepository.findAll();
    }
}
