package cogent.com.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {
	@Id
	private int id;

	private String recipient;
	private String msgBody;
	private String subject;
	private String attachment;
}
