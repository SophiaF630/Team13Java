package sg.iss.caps.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentCourseRegisterDto {
	private String studentId = "";
	private String[] courseIndexes = {};

	public String[] getCourseIndexes() {
		return courseIndexes;
	}
	public void setCourseIndexes(String[] courseIndexes) {
		this.courseIndexes = courseIndexes;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

}
