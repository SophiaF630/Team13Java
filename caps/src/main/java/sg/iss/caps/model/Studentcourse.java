package sg.iss.caps.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;


/**
 * The persistent class for the studentcourse database table.
 * 
 */
@Entity
@NamedQuery(name="Studentcourse.findAll", query="SELECT s FROM Studentcourse s")
@Data
@AllArgsConstructor
public class Studentcourse implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private StudentcoursePK id;

	private int CAGrade;

	@Temporal(TemporalType.TIMESTAMP)
	private Date enrollTime;

	private int examGrade;

	private String status;

	//bi-directional many-to-one association to Course
	@ManyToOne
	@JoinColumn(name="CourseIndex",insertable=false, updatable=false)
	private Course course;

	//bi-directional many-to-one association to Student
	@ManyToOne
	@JoinColumn(name="Student_StudentID",insertable=false, updatable=false)
	private Student student;

	public Studentcourse() {
	}

	public StudentcoursePK getId() {
		return this.id;
	}

	public void setId(StudentcoursePK id) {
		this.id = id;
	}

	public int getCAGrade() {
		return this.CAGrade;
	}

	public void setCAGrade(int CAGrade) {
		this.CAGrade = CAGrade;
	}

	public Date getEnrollTime() {
		return this.enrollTime;
	}

	public void setEnrollTime(Date enrollTime) {
		this.enrollTime = enrollTime;
	}

	public int getExamGrade() {
		return this.examGrade;
	}

	public void setExamGrade(int examGrade) {
		this.examGrade = examGrade;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}