package cogent.com.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chat")
public class Chat {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String from_user;
	private String to_user;
	private String message;
	private String datetime;

	public Chat(String from_user, String to_user, String message, String datetime) {
		this.from_user = from_user;
		this.to_user = to_user;
		this.message = message;
		this.datetime = datetime;
	}
}
