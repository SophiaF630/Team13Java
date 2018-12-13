package sg.iss.caps.services;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import sg.iss.caps.model.Course;
import sg.iss.caps.model.Student;
import sg.iss.caps.model.Studentcourse;
import sg.iss.caps.repo.CourseRepository;
import sg.iss.caps.repo.StudentCourseRepository;
import sg.iss.caps.repo.StudentRepository;

@Service
@Transactional
public class StudentServicesImpl implements StudentService {

	@Resource
	StudentRepository srepo;
	@Resource
	CourseRepository crepo;
	@Resource
	StudentCourseRepository screpo;
	@Resource
	CourseService cserve;
	
	@Override
	public ArrayList<Student> findAllStudents() {
		return (ArrayList<Student>) srepo.findAll();
	}

	@Override
	public Student findStudent(String sid) {
		System.out.println("StudentID"+sid);
		
		Student student = srepo.findById(sid).get();
		System.out.println(student.toString());
		return student;
	}	
	
	@Override
	public Student findByStudentID(String sid) {
		Student student = srepo.findByStudentID(sid);
		return student;
	}

	@Override
	public Student createStudent(Student s) {
		return srepo.saveAndFlush(s);
	}

	@Override
	public Student updateStudent(Student s) {
		return srepo.saveAndFlush(s);
	}

	@Override
	public void removeStudent(Student s) {
		srepo.delete(s);
	}

	@Override
	public ArrayList<Student> findStudentsByCriteria(Student student) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ArrayList<Course> findCurrentCoursesByStudentID(String studentID) {
		ArrayList<Studentcourse> temp = screpo.findByStudentID(studentID);
		ArrayList<Course> result = new ArrayList<Course>();
		for (Studentcourse c : temp) {
			result.add(c.getCourse());
		}
		return (ArrayList<Course>) cserve.selectCurrentCourse(result);
	}
	
	@Override
	public ArrayList<Course> findAllStudentsCourseDetails() {
		return (ArrayList<Course>) crepo.findAll();
	}
	
	@Override	
	public ArrayList<Course> findAllCurrentSemesterCourse(Date ed) {
		return (ArrayList<Course>) crepo.findAllCurrentSemesterCourse(ed);
	}
	
	@Override	
	public ArrayList<Studentcourse> findAllStudentCourseByStatus(String status, String sid) {
		return (ArrayList<Studentcourse>) screpo.findAllStudentCourseByStatus(status, sid);
	}

	@Override
	public ArrayList<Course> findHistoryCoursesByStudentID(String studentID) {
		ArrayList<Studentcourse> temp = screpo.findByStudentID(studentID);
		ArrayList<Course> result = new ArrayList<Course>();
		for (Studentcourse c : temp) {
			result.add(c.getCourse());
		}
		return result;
	}
	
	
	@Override	
	public ArrayList<Studentcourse> studentViewGrade(String studentID, String status) {		
		return (ArrayList<Studentcourse>) screpo.studentViewGrade(studentID, status);
	}
	
}
