package school.students.repository;

import org.springframework.data.repository.CrudRepository;
import school.students.model.Philosopher;

public interface PhilosopherRepository extends CrudRepository<Philosopher, Long> {
}
