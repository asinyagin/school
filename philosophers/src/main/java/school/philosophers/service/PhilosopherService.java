package school.philosophers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.philosophers.model.Philosopher;
import school.philosophers.repository.PhilosopherRepository;

import java.util.Iterator;
import java.util.Random;

@Service
public class PhilosopherService {

    @Autowired
    private PhilosopherRepository philosopherRepository;

    private Random random = new Random(System.currentTimeMillis());

    public Philosopher randomPhilosopher() {
        int num = random.nextInt((int) philosopherRepository.count());
        Iterator<Philosopher> it = philosopherRepository.findAll().iterator();
        while (num-- > 0) it.next();
        return it.next();
    }

    public Philosopher findOneByName(String name) {
        return philosopherRepository.findOneByName(name);
    }
}
