package cogent.com.dto;

import java.time.LocalDateTime;
import java.util.List;

import cogent.com.entity.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {

	private int id;
	private String descriptionQuestion;
	private String imageSrc;
	private String datetime;
	private boolean status;
	private String topic;
	private String title;
	private List<Answer> answers;
	private String qcreated_by;
	private String qapproved_by;

	public QuestionDTO(String descriptionQuestion, String topic, String title) {
		this.descriptionQuestion = descriptionQuestion;
		this.topic = topic;
		this.title = title;
		this.status = false;
		this.datetime = LocalDateTime.now().toString();
	}
}
