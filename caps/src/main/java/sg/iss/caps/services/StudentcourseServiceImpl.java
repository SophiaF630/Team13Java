package sg.iss.caps.services;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import sg.iss.caps.model.Student;
import sg.iss.caps.model.Studentcourse;
import sg.iss.caps.model.StudentcoursePK;
import sg.iss.caps.repo.StudentCourseRepository;
import sg.iss.caps.repo.StudentRepository;

@Service
@Transactional
public class StudentcourseServiceImpl implements StudentcourseService {
	
	
	@Resource
	StudentRepository srepo;
	@Resource
	StudentCourseRepository screpo;
	
	@Override
	public Studentcourse createStudentCourse(Studentcourse sc){
		return screpo.saveAndFlush(sc);
	}
	
	@Override
	public StudentcoursePK findStudentcoursePK(String sid, String ci) {	
		StudentcoursePK scpk = new StudentcoursePK();
		scpk.setStudent_StudentID(sid);
		scpk.setCourseIndex(ci);
		return scpk;
	}

}
