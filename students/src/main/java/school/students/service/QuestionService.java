package school.students.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.students.model.Question;
import school.students.repository.QuestionRepository;

import java.util.Iterator;
import java.util.Random;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    private Random random = new Random(System.currentTimeMillis());

    public Iterable<Question> findAll() {
        return questionRepository.findAll();
    }

    public Question randomQuestion() {
        int num = random.nextInt((int)questionRepository.count());
        Iterator<Question> it = questionRepository.findAll().iterator();
        while (num-- > 0) it.next();
        return it.next();
    }

}
