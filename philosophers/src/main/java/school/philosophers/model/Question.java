package school.philosophers.model;

import javax.persistence.*;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "philosopher")
    private Philosopher philosopher;

    private String text;

    @ManyToOne
    @JoinColumn(name = "answer")
    private Answer answer;

    public Question() {
    }

    public Question(Philosopher philosopher, String text, Answer answer) {
        this.philosopher = philosopher;
        this.text = text;
        this.answer = answer;
    }
}
