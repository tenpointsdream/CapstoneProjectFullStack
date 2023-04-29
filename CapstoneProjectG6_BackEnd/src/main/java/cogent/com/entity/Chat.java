package cogent.com.entity;

import lombok.*;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
