package sg.iss.caps.services;

import java.util.ArrayList;

import sg.iss.caps.model.Course;
import sg.iss.caps.model.Lecturer;
import sg.iss.caps.model.Student;

public interface CourseService {
	ArrayList<Course> findAllCourse();
	Course findCourseByID(String courseID);
	Course createCourse(Course course);
	Course updateCourse(Course course);
	void removeCourse(Course course);
	ArrayList<Course> selectCurrentCourse(ArrayList<Course> courses);
	ArrayList<Course> findCurrentCourseByLecturer(Lecturer lecturer);
	ArrayList<Student> findCurrentStudentByCourse(String CourseID);
}
