package cogent.com.entity;

import lombok.*;
import javax.persistence.*;

import cogent.com.util.UserType;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String username;
	private String password;
	private String email;
	private UserType userType;

	public User(String name, String username, String password, String email, UserType userType) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.userType = userType;
	}
}
