package cogent.com.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "question")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String descriptionQuestion;
	private String imageSrc;
	private String datetime;
	private String status;
	private String topic;
	private String title;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "question")
	@JsonIgnore
	private List<Answer> answers;
	@OneToOne
	@JoinColumn(name = "created_by_id")
	private User qcreated_by;

	@OneToOne
	@JoinColumn(name = "approved_by_id")
	private User qapproved_by;

	public Question(String descriptionQuestion, String imageSrc, String datetime, String status, String topic,
			String title, List<Answer> answers, User qcreated_by, User qapproved_by) {
		this.descriptionQuestion = descriptionQuestion;
		this.imageSrc = imageSrc;
		this.datetime = datetime;
		this.status = status;
		this.topic = topic;
		this.title = title;
		this.answers = answers;
		this.qcreated_by = qcreated_by;
		this.qapproved_by = qapproved_by;
	}
}
