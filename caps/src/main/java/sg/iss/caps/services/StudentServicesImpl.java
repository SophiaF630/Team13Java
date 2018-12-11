package sg.iss.caps.services;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import sg.iss.caps.model.Course;
import sg.iss.caps.model.Student;
import sg.iss.caps.repo.CourseRepository;
import sg.iss.caps.repo.StudentRepository;

@Service
public class StudentServicesImpl implements StudentService {

	@Resource
	StudentRepository srepo;
	@Resource
	CourseRepository crepo;
	
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
	public ArrayList<Course> findAllStudentsCourseDetails() {
		return (ArrayList<Course>) crepo.findAll();
	}
	
	@Override	
	public ArrayList<Course> findAllSelectedCourse(String faculty) {
		return (ArrayList<Course>) crepo.findAllSelectedCourse(faculty);
	}

}
