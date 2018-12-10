package sg.iss.caps.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the studentcourse database table.
 * 
 */
@Embeddable
public class StudentcoursePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String student_StudentID;

	@Column(insertable=false, updatable=false)
	private String courseIndex;

	public StudentcoursePK() {
	}
	public String getStudent_StudentID() {
		return this.student_StudentID;
	}
	public void setStudent_StudentID(String student_StudentID) {
		this.student_StudentID = student_StudentID;
	}
	public String getCourseIndex() {
		return this.courseIndex;
	}
	public void setCourseIndex(String courseIndex) {
		this.courseIndex = courseIndex;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof StudentcoursePK)) {
			return false;
		}
		StudentcoursePK castOther = (StudentcoursePK)other;
		return 
			this.student_StudentID.equals(castOther.student_StudentID)
			&& this.courseIndex.equals(castOther.courseIndex);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.student_StudentID.hashCode();
		hash = hash * prime + this.courseIndex.hashCode();
		
		return hash;
	}
}