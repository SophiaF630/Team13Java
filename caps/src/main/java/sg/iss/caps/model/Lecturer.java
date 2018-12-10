package sg.iss.caps.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "Lecturer")
@Data
@AllArgsConstructor
public class Lecturer {

	@Id
	@Column(name = "LectureID")
	private String lectureID;
	@Column(name = "Faulty")
	private String faulty;
}
