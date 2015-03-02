package school.students.model;

import javax.persistence.*;

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

    public Philosopher() {
    }

    public Philosopher(Student student, String name) {
        this.student = student;
        this.name = name;
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
}
