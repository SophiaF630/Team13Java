package sg.iss.caps.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "User")
@Data
@AllArgsConstructor
public class User {
	
	@Id
	@Column(name = "UserID")
	private String userID;
	@Column(name = "UserTpe")
	private String userType;
	@Column(name = "Password")
	private String password;
	@Column(name = "LastName")
	private String lastName;
	@Column(name = "FirstMidName")
	private String firstMidName;
	@Column(name = "Phone")
	private int phone;
	@Column(name = "Email")
	private String email;
	@Column(name = "Address")
	private String address;
	
}
