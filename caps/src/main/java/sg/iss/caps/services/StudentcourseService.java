package sg.iss.caps.services;

import java.util.ArrayList;
import java.util.Optional;

import sg.iss.caps.model.StudentGrade;
import sg.iss.caps.model.Studentcourse;
import sg.iss.caps.model.StudentcoursePK;

public interface StudentcourseService {
	Optional<Studentcourse> findStudentCourse(StudentcoursePK scPK);
	
	Studentcourse createStudentCourse(Studentcourse sc);

	StudentcoursePK findStudentcoursePK(String sid, String ci);
	
	double calculateStudentGPA(String sid);

	Studentcourse updateStudentCourse(Studentcourse sc);

	int getStudentCount(String courseIndex);

	double calculateStudentCGPA(String sid);

	ArrayList<Studentcourse> Viewcoursebycourseindex(String CourseIndex);
}
