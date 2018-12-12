package sg.iss.caps.services;

import java.util.ArrayList;

import sg.iss.caps.model.Course;

public interface CourseService {
	ArrayList<Course> selectCurrentCourse(ArrayList<Course> courses);
}
