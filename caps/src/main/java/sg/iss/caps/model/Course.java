package sg.iss.caps.model;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;


@Entity
@Table(name = "course")
@Data
@AllArgsConstructor
public class Course {

	@Id
	@Column(name = "CourseIndex")
	private String courseIndex;
	@Column(name = "CourseID")
	private String courseID;
	@Column(name = "CourseName")
	private String courseName;
	@Column(name = "CourseDescription")
	private String courseDescription;
	@Column(name = "Faulty")
	private String faulty;
	@Column(name = "Credits")
	private int credits;
	@Column(name = "ClassSize")
	private int classSize;
	@Column(name = "LectureSchedule")
	private String lectureSchedule;
	@Column(name = "StartDate")
	private LocalDateTime startDate;
	@Column(name = "EndDate")
	private LocalDateTime endDate;
	
	
	
}
