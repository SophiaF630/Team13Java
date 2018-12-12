package sg.iss.caps.services;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import sg.iss.caps.model.Course;

public class CourseServiceImpl implements CourseService {

	@Override
	public ArrayList<Course> selectCurrentCourse(ArrayList<Course> courses) {
		SimpleDateFormat format = new SimpleDateFormat("YYYY-mm-dd");
		Date datenow = new Date();
		for (int i = courses.size()-1;i>=0;i--) {
			if (courses.get(i).getEndDate().before(datenow)) {
				courses.remove(courses.get(i));
			}
		}
		// TODO Auto-generated method stub
		return courses;
	}
}
