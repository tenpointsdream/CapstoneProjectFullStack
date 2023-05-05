package cogent.com.dto;

import cogent.com.entity.Question;
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
	private String approved_by;
	private String created_by;

}
