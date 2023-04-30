package cogent.com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "question")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String description;
	private String imageSrc;
	private String datetime;
	private boolean status;
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

	public Question(String description, String imageSrc, String datetime, boolean status, String topic,
					String title, List<Answer> answers, User qcreated_by, User qapproved_by) {
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String descriptionQuestion) {
		this.description = descriptionQuestion;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
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
