package cogent.com.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

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

	public Question() {}

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescriptionQuestion() {
		return descriptionQuestion;
	}

	public void setDescriptionQuestion(String descriptionQuestion) {
		this.descriptionQuestion = descriptionQuestion;
	}

	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public User getQcreated_by() {
		return qcreated_by;
	}

	public void setQcreated_by(User qcreated_by) {
		this.qcreated_by = qcreated_by;
	}

	public User getQapproved_by() {
		return qapproved_by;
	}

	public void setQapproved_by(User qapproved_by) {
		this.qapproved_by = qapproved_by;
	}

}
