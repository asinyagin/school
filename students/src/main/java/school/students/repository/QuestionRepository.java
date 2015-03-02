package school.students.repository;

import org.springframework.data.repository.CrudRepository;
import school.students.model.Question;

public interface QuestionRepository extends CrudRepository<Question, Long> {
}
