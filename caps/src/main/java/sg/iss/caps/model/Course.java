package sg.iss.caps.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the course database table.
 * 
 */
@Entity
@NamedQuery(name="Course.findAll", query="SELECT c FROM Course c")
@Data
@AllArgsConstructor
public class Course implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@Id
	private String courseIndex;

	private int classSize;

	@Lob
	private String courseDescription;

	private String courseID;

	private String courseName;

	private int credits;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	private String faculty;

	private String lectureSchedule;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	//bi-directional many-to-many association to Lecturer
	@ManyToMany(mappedBy="courses")
	private List<Lecturer> lecturers;

	//bi-directional many-to-one association to Lecturercourse
	@OneToMany(mappedBy="course")
	private Set<Lecturercourse> lecturercourses = new HashSet<Lecturercourse>();

	//bi-directional many-to-one association to Studentcourse
	@OneToMany(mappedBy="course")
	private Set<Studentcourse> studentcourses = new HashSet<Studentcourse>();

	public Course() {
	}

	public String getCourseIndex() {
		return this.courseIndex;
	}

	public void setCourseIndex(String courseIndex) {
		this.courseIndex = courseIndex;
	}

	public int getClassSize() {
		return this.classSize;
	}

	public void setClassSize(int classSize) {
		this.classSize = classSize;
	}

	public String getCourseDescription() {
		return this.courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public String getCourseID() {
		return this.courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCredits() {
		return this.credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getFaculty() {
		return this.faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getLectureSchedule() {
		return this.lectureSchedule;
	}

	public void setLectureSchedule(String lectureSchedule) {
		this.lectureSchedule = lectureSchedule;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public List<Lecturer> getLecturers() {
		return this.lecturers;
	}

	public void setLecturers(List<Lecturer> lecturers) {
		this.lecturers = lecturers;
	}

	public Set<Lecturercourse> getLecturercourses() {
		return this.lecturercourses;
	}

	public void setLecturercourses(Set<Lecturercourse> lecturercourses) {
		this.lecturercourses = lecturercourses;
	}	

	public Lecturercourse addLecturercours(Lecturercourse lecturercours) {
		getLecturercourses().add(lecturercours);
		lecturercours.setCourse(this);

		return lecturercours;
	}

	public Lecturercourse removeLecturercours(Lecturercourse lecturercours) {
		getLecturercourses().remove(lecturercours);
		lecturercours.setCourse(null);

		return lecturercours;
	}

	public Set<Studentcourse> getStudentcourses() {
		return this.studentcourses;
	}

	public void setStudentcourses(Set<Studentcourse> studentcourses) {
		this.studentcourses = studentcourses;
	}

	public Studentcourse addStudentcours(Studentcourse studentcours) {
		getStudentcourses().add(studentcours);
		studentcours.setCourse(this);

		return studentcours;
	}

	public Studentcourse removeStudentcours(Studentcourse studentcours) {
		getStudentcourses().remove(studentcours);
		studentcours.setCourse(null);

		return studentcours;
	}

	

}