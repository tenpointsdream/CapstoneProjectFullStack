package cogent.com.entity;

import lombok.*;
import  javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "answer")
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String description_answer;
	private String img_src;
	private String status;
	private String datetime;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "question_id")
	private Question question;

	@OneToOne
	@JoinColumn(name = "approved_by_id")
	private User approved_by;

	@OneToOne
	@JoinColumn(name = "created_by_id")
	private User created_by;
}
