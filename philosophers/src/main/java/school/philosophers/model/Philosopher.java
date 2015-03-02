package school.philosophers.model;

import javax.persistence.*;

@Entity
@Table(indexes = {
        @Index(unique = true, columnList = "name")
})
public class Philosopher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    public Philosopher() {
    }

    public Philosopher(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
