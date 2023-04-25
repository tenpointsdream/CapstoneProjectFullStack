package cogent.com.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String description_question;
    private String image_src;
    private String datetime;
    private String status;
    private String topics;
    private String title;
    @OneToMany(mappedBy = "question", fetch=FetchType.EAGER)
    private List<Answer> answers;
    @OneToOne
    private User qcreated_by;
    @OneToOne
    private User qapproved_by;
}
