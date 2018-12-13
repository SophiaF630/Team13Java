package sg.iss.caps.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the lecturer database table.
 * 
 */
@Entity
@NamedQuery(name="Lecturer.findAll", query="SELECT l FROM Lecturer l")
@Data
@AllArgsConstructor
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
			@JoinColumn(name="LecturerID",insertable=false, updatable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="CourseIndex",insertable=false, updatable=false)
			}
		)
	private Set<Course> courses = new HashSet<Course>();

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

	public Set<Course> getCourses() {
		return this.courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}