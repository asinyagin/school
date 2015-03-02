package school.students.model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Philosopher> philosophers = new LinkedList<>();

    public List<Philosopher> getPhilosophers() {
        return philosophers;
    }
}
