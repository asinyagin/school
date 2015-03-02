package school.philosophers.repository;

import org.springframework.data.repository.CrudRepository;
import school.philosophers.model.Philosopher;

public interface PhilosopherRepository extends CrudRepository<Philosopher, Long> {

    Philosopher findOneByName(String name);
}
