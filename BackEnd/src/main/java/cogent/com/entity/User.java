package cogent.com.entity;

import cogent.com.util.UserType;

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
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String username;
	private String password;
	private String email;
	@Column(name = "usertype")
	private UserType userType;

	public User(String name, String username, String password, String email, UserType userType) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.userType = userType;
	}
}
