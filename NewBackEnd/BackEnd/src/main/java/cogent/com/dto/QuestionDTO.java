package cogent.com.dto;

import java.util.List;

import cogent.com.entity.Answer;
import cogent.com.entity.User;
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
	private User qcreated_by;
	private User qapproved_by;
}
