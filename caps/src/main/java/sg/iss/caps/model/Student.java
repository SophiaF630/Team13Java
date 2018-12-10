package sg.iss.caps.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "Student")
@Data
@AllArgsConstructor
public class Student {
	
	@Id
	@Column(name = "StudentID")
	private String studentID;
	@Column(name = "EnrollmentDate")
	private LocalDateTime enrollmentDate;

}
