package school.students.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Philosopher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private String name;

    private boolean fired = false;

    @ManyToOne
    @JoinColumn(name = "question")
    private Question question;

    @ElementCollection
    private Set<String> answers;

    public Philosopher() {
    }

    public Philosopher(Student student, String name) {
        this.student = student;
        this.name = name;
    }

    public Philosopher(Student student, String name, boolean fired) {
        this.student = student;
        this.name = name;
        this.fired = fired;
    }

    public String getName() {
        return name;
    }

    public boolean isFired() {
        return fired;
    }

    public void setFired(boolean fired) {
        this.fired = fired;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Set<String> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<String> answers) {
        this.answers = answers;
    }
}
