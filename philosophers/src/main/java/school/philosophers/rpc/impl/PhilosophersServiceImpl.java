package school.philosophers.rpc.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import school.philosophers.model.Answer;
import school.philosophers.model.Philosopher;
import school.philosophers.model.Question;
import school.philosophers.rpc.PhilosophersService;
import school.philosophers.service.AnswerService;
import school.philosophers.service.PhilosopherService;
import school.philosophers.service.QuestionService;

@Component
public class PhilosophersServiceImpl implements PhilosophersService {

    private static final Log log = LogFactory.getLog(PhilosophersServiceImpl.class);

    @Autowired
    private PhilosopherService philosopherService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuestionService questionService;

    @Override
    public String GET_PHIL(Long studentId) {
        log.info(String.format("json-rpc: GET_PHIL(%d)", studentId));
        int attempts = 5;
        Philosopher philosopher = null;
        while (attempts-- > 0) {
            philosopher = philosopherService.randomPhilosopher();
            if (!philosopher.getGoneStudents().contains(studentId)) break;
        }
        String name;
        if (attempts == 0) {
            log.error(String.format("Could not find philosopher with 5 attempts."));
            name = "Error here.";
        } else {
            name = philosopher.getName();
        }
        return name;
    }

    @Override
    public String ASK_PHIL(String name, String question) {
        log.info(String.format("json-rpc: ASK_PHIL(%s, %s)", name, question));
        Philosopher philosopher = philosopherService.findOneByName(name);
        String answer;
        if (philosopher == null) {
            log.error(String.format("Could not find philosopher with name %s.", name));
            answer = "Error here.";
        } else {
            Answer answerEntity = answerService.randomAnswer();
            questionService.save(new Question(philosopher, question, answerEntity));
            answer = answerEntity.getText();
        }
        return answer;
    }

    @Override
    public void FIRE_PHIL(String name, Long studentId) {
        log.info(String.format("json-rpc: FIRE_PHIL(%s, %d)", name, studentId));
        Philosopher philosopher = philosopherService.findOneByName(name);
        if (philosopher == null) {
            log.error(String.format("Could not find philosopher with name %s.", name));
        } else {
            philosopher.getGoneStudents().add(studentId);
            philosopherService.save(philosopher);
        }
    }
}
