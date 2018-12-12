package sg.iss.caps.services;

import java.util.ArrayList;

import sg.iss.caps.model.Course;
import sg.iss.caps.model.Lecturer;

public interface CourseService {
	ArrayList<Course> selectCurrentCourse(ArrayList<Course> courses);
	ArrayList<Course> findCurrentCourseByLecturer(Lecturer lecturer);
}
