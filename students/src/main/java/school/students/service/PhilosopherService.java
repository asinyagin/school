package school.students.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.students.model.Philosopher;
import school.students.repository.PhilosopherRepository;

@Service
public class PhilosopherService {

    @Autowired
    private PhilosopherRepository philosopherRepository;

    public Philosopher save(Philosopher philosopher) {
        return philosopherRepository.save(philosopher);
    }
}
