package sg.iss.caps.services;

import java.util.ArrayList;
import java.util.Date;

import sg.iss.caps.model.Course;
import sg.iss.caps.model.Student;
import sg.iss.caps.model.Studentcourse;

public interface StudentService {

	ArrayList<Student> findAllStudents();

	Student findStudent(String sid);
	
	Student findByStudentID(String sid);

	Student createStudent(Student s);

	Student updateStudent(Student s);

	void removeStudent(Student s);

	ArrayList<Student> findStudentsByCriteria(Student student);

	ArrayList<Course> findAllStudentsCourseDetails();
	
	ArrayList<Course> findCurrentCoursesByStudentID(String studentID);
	//this part is to query the current class for student
	
	ArrayList<Course> findHistoryCoursesByStudentID(String studentID);
	//this part is to query the history class for student

	ArrayList<Course> findAllCurrentSemesterCourse(Date ed);
	
	ArrayList<Studentcourse> studentViewGrade(String studentID, String status);
	
	ArrayList<Studentcourse> findAllStudentCourseByStatus(String status, String sid);

}