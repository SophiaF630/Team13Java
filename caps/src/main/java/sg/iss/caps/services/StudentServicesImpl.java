package sg.iss.caps.services;

import java.util.ArrayList;

import javax.annotation.Resource;

import sg.iss.caps.model.Student;
import sg.iss.caps.repo.StudentRepository;

public class StudentServicesImpl implements StudentService {

	@Resource
	StudentRepository srepo;
	
	@Override
	public ArrayList<Student> findAllStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student findStudent(String nric) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student createStudent(Student s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student updateStudent(Student s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeStudent(Student s) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Student> findStudentsByCriteria(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

}
