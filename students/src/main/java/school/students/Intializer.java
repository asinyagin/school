package school.students;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import school.students.model.Question;
import school.students.repository.QuestionRepository;

import java.util.Arrays;

@Component
public class Intializer implements InitializingBean {

    private static final String[] QUESTIONS = {
            "Is it worse to fail at something or never attempt it in the first place?",
            "If you could choose just one thing to change about the world, what would it be?",
            "To what extent do you shape your own destiny, and how much is down to fate?",
            "Does nature shape our personalities more than nurture?",
            "Should people care more about doing the right thing, or doing things right?",
            "What one piece of advice would you offer to a newborn infant?",
            "Where is the line between insanity and creativity?",
            "What is true happiness?",
            "What things hold you back from doing the things that you really want to?",
            "What makes you, you?",
            "What is the truth?",
            "What is reality?",
            "Do you make your own decisions, or let others make them for you?",
            "What makes a good friend?",
            "Why do people fear losing things that they do not even have yet?",
            "Who defines good and evil?",
            "What is the difference between living and being alive?",
            "Is a “wrong” act okay if nobody ever knows about it?",
            "Who decides what morality is?",
            "How do you know that your experience of consciousness is the same as other people’s experience of consciousness?",
            "What is true strength?",
            "What is true love?",
            "Is a family still relevant in the modern world?",
            "What role does honour play in today’s society?",
            "If money cannot buy happiness, can you ever be truly happy with no money?",
            "How do you know your perceptions are real?",
            "How much control do you have over your life?",
            "What is freedom?",
            "Isn’t one person’s terrorist another person’s freedom fighter?",
            "What happens after we die?"
    };

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        initQuestions();
    }

    private void initQuestions() {
        Arrays.asList(QUESTIONS).stream()
                .map(Question::new)
                .forEach(questionRepository::save);
    }
}
