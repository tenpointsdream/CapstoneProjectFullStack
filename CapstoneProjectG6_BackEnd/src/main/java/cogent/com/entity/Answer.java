package cogent.com.entity;

import javax.persistence.*;

@Entity
@Table(name = "answer")
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String description_answer;
	private String img_src;
	private boolean status;
	private String datetime;

	@ManyToOne
	private Question question;

	private String approved_by;
	
	private String created_by;

	public Answer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Answer(String description_answer, String img_src, boolean status, String datetime, Question question,
			String approved_by, String created_by) {
		super();
		this.description_answer = description_answer;
		this.img_src = img_src;
		this.status = status;
		this.datetime = datetime;
		this.question = question;
		this.approved_by = approved_by;
		this.created_by = created_by;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription_answer() {
		return description_answer;
	}

	public void setDescription_answer(String description_answer) {
		this.description_answer = description_answer;
	}

	public String getImg_src() {
		return img_src;
	}

	public void setImg_src(String img_src) {
		this.img_src = img_src;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getApproved_by() {
		return approved_by;
	}

	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

}
