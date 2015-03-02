package school.students.repository;

import org.springframework.data.repository.CrudRepository;
import school.students.model.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
