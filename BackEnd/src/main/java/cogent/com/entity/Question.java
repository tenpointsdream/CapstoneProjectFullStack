package cogent.com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "question")
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String descriptionQuestion;
	private String imageSrc;
	private String datetime;
	private boolean status;
	private String topic;
	private String title;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "question")
	@JsonIgnore
	private List<Answer> answers;

	private String qcreated_by;

	private String qapproved_by;
}
