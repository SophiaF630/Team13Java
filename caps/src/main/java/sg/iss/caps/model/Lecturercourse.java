package sg.iss.caps.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * The persistent class for the lecturercourse database table.
 * 
 */
@Entity
@NamedQuery(name="Lecturercourse.findAll", query="SELECT l FROM Lecturercourse l")
@Data
@AllArgsConstructor
public class Lecturercourse implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LecturercoursePK id;

	//bi-directional many-to-one association to Course
	@ManyToOne
	@JoinColumn(name="CourseIndex",insertable=false, updatable=false)
	private Course course;

	public Lecturercourse() {
	}

	public LecturercoursePK getId() {
		return this.id;
	}

	public void setId(LecturercoursePK id) {
		this.id = id;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}