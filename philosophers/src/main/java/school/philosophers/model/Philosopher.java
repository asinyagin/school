package school.philosophers.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ElementCollection
    private Set<Long> goneStudents = new HashSet<>();

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

    public Set<Long> getGoneStudents() {
        return goneStudents;
    }
}
