package cogent.com.dto;

import cogent.com.entity.Question;
import cogent.com.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnswerDTO {

	private int id;
	private String description_answer;
	private String img_src;
	private boolean status;
	private String datetime;
	private Question question;
	private User approved_by;
	private User created_by;

}
