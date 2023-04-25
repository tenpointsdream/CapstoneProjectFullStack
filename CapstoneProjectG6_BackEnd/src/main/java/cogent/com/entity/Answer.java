package cogent.com.entity;

import lombok.*;
import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String description_answer;
    private String img_src;
    private String status;
    private String datetime;

    @ManyToOne
    private Question question;

    @OneToOne
    private User approved_by;

    @OneToOne
    private User created_by;

}
