package school.philosophers.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(indexes = {
        @Index(unique = true, columnList = "name")
})
public class Philosopher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToMany(mappedBy = "philosopher")
    private List<Question> questions = new ArrayList<>();

    public Philosopher() {
    }

    public Philosopher(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
