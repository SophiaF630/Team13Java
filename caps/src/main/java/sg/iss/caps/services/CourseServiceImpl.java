package sg.iss.caps.services;

import java.util.Date;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import sg.iss.caps.model.Course;
import sg.iss.caps.model.Lecturer;
import sg.iss.caps.model.Student;
import sg.iss.caps.model.Studentcourse;
import sg.iss.caps.repo.CourseRepository;
import sg.iss.caps.repo.LecturerCourseRepository;
import sg.iss.caps.repo.StudentCourseRepository;
@Service
@Transactional
public class CourseServiceImpl implements CourseService {
	@Resource
	LecturerCourseRepository lcRepo;
	@Resource
	CourseRepository cRepo;
	@Resource
	StudentCourseRepository scRepo;

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

	@Override
	public ArrayList<Course> findCurrentCourseByLecturer(Lecturer lecturer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Course> findAllCourse() {
		// TODO Auto-generated method stub
		return (ArrayList<Course>) cRepo.findAll() ;
	}

	@Override
	public Course findCourseByID(String courseID) {
		// TODO Auto-generated method stub
		return cRepo.findById(courseID).get();
	}

	@Override
	public Course createCourse(Course course) {
		// TODO Auto-generated method stub
		return cRepo.saveAndFlush(course);
	}

	@Override
	public Course updateCourse(Course course) {
		// TODO Auto-generated method stub
		return cRepo.saveAndFlush(course);
	}

	@Override
	public void removeCourse(Course course) {
		// TODO Auto-generated method stub
		//this part need some logic error about how to delete enough records
		cRepo.delete(course);
	}

	@Override
	public ArrayList<Student> findCurrentStudentByCourse(String courseID) {
		// TODO Auto-generated method stub
		ArrayList<Studentcourse> temp = scRepo.findByCourseIndex(courseID);
		ArrayList<Student> result = new ArrayList<Student>();
		for (Studentcourse c : temp) {
			result.add(c.getStudent());
		}
		return result;
	}

}
