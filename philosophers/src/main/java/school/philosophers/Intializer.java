package school.philosophers;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import school.philosophers.model.Answer;
import school.philosophers.model.Philosopher;
import school.philosophers.repository.AnswerRepository;
import school.philosophers.repository.PhilosopherRepository;

import java.util.Arrays;

@Component
public class Intializer implements InitializingBean {

    private static final String[] PHILOSOPHERS = {
            "John Locke",
            "Epicurus",
            "Zeno of Citium",
            "Avicenna",
            "Thomas Aquinas",
            "Confucius",
            "Rene Descartes",
            "Paul of Tarsus",
            "Plato",
            "Aristotle"
    };

    private static final String[] ANSWERS = {
            "Signs point to yes.",
            "Yes.",
            "Reply hazy, try again.",
            "Without a doubt.",
            "My sources say no.",
            "As I see it, yes.",
            "You may rely on it.",
            "Concentrate and ask again.",
            "Outlook not so good.",
            "It is decidedly so.",
            "Better not tell you now.",
            "Very doubtful.",
            "Yes - definitely.",
            "It is certain.",
            "Cannot predict now.",
            "Most likely.",
            "Ask again later.",
            "My reply is no.",
            "Outlook good.",
            "Don't count on it."
    };

    @Autowired
    private PhilosopherRepository philosopherRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        initPhilosophers();
        initAnswers();
    }

    private void initPhilosophers() {
        Arrays.asList(PHILOSOPHERS).stream()
                .map(Philosopher::new)
                .forEach(philosopherRepository::save);
    }

    private void initAnswers() {
        Arrays.asList(ANSWERS).stream()
                .map(Answer::new)
                .forEach(answerRepository::save);
    }
}
