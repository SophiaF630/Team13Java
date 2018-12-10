package sg.iss.caps.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the lecturer database table.
 * 
 */
@Entity
@NamedQuery(name="Lecturer.findAll", query="SELECT l FROM Lecturer l")
public class Lecturer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String lecturer_LecturerID;

	private String faculty;

	//bi-directional many-to-many association to Course
	@ManyToMany
	@JoinTable(
		name="lecturercourse"
		, joinColumns={
			@JoinColumn(name="LecturerID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="CourseIndex")
			}
		)
	private List<Course> courses;

	//bi-directional one-to-one association to User
	@OneToOne
	@JoinColumn(name="Lecturer_LecturerID")
	private User user;

	public Lecturer() {
	}

	public String getLecturer_LecturerID() {
		return this.lecturer_LecturerID;
	}

	public void setLecturer_LecturerID(String lecturer_LecturerID) {
		this.lecturer_LecturerID = lecturer_LecturerID;
	}

	public String getFaculty() {
		return this.faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public List<Course> getCourses() {
		return this.courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}