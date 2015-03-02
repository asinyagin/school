package school.philosophers.repository;

import org.springframework.data.repository.CrudRepository;
import school.philosophers.model.Answer;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
}
