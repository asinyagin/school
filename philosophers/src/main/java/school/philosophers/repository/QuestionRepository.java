package school.philosophers.repository;

import org.springframework.data.repository.CrudRepository;
import school.philosophers.model.Question;

public interface QuestionRepository extends CrudRepository<Question, Long> {
}
