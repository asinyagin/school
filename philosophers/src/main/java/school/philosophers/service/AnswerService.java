package school.philosophers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.philosophers.model.Answer;
import school.philosophers.repository.AnswerRepository;

import java.util.Iterator;
import java.util.Random;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    private Random random = new Random(System.currentTimeMillis());

    public Answer randomAnswer() {
        int num = random.nextInt((int) answerRepository.count());
        Iterator<Answer> it = answerRepository.findAll().iterator();
        while (num-- > 0) it.next();
        return it.next();
    }
}
