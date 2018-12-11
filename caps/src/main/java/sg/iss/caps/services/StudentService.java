package sg.iss.caps.services;

import java.util.ArrayList;

import sg.iss.caps.model.Course;
import sg.iss.caps.model.Student;

public interface StudentService {

	ArrayList<Student> findAllStudents();

	Student findStudent(String nric);

	Student createStudent(Student s);

	Student updateStudent(Student s);

	void removeStudent(Student s);

	ArrayList<Student> findStudentsByCriteria(Student student);
	
	ArrayList<Course> findAllStudentsCourseDetails();

}