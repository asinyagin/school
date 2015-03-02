package school.philosophers.model;

import javax.persistence.*;

@Entity
@Table(indexes = {
        @Index(unique = true, columnList = "text")
})
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String text;

    public Answer() {
    }

    public Answer(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
