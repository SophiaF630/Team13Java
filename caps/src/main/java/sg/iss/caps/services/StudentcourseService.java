package sg.iss.caps.services;

import sg.iss.caps.model.StudentGrade;
import sg.iss.caps.model.Studentcourse;
import sg.iss.caps.model.StudentcoursePK;

public interface StudentcourseService {
	
	Studentcourse createStudentCourse(Studentcourse sc);

	StudentcoursePK findStudentcoursePK(String sid, String ci);
	
	double calculateStudentGPA(String sid);

}
