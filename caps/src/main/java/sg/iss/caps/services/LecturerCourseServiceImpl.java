package sg.iss.caps.services;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.iss.caps.model.Lecturer;
import sg.iss.caps.model.Lecturercourse;
import sg.iss.caps.repo.LecturerCourseRepository;
import sg.iss.caps.repo.LecturerRepository;

@Service
@Transactional
public class LecturerCourseServiceImpl implements LecturerCourseService {

	@Resource
	LecturerCourseRepository lcrepo;
	
	/*@Override
	public ArrayList<Lecturercourse> ViewcoursebylectureID(String lid) {
		
		return (ArrayList<Lecturercourse>) lcrepo.ViewcoursebylectureID(lid) ;
	}*/
	
	@Override
	public ArrayList<Lecturercourse> findAllLecturer() {
		return (ArrayList<Lecturercourse>)lcrepo.findAll();
	}
	
	@Override
	public ArrayList<Lecturercourse> ViewcoursebylectureID(String lid) {
		
		return (ArrayList<Lecturercourse>) lcrepo.ViewcoursebylectureID(lid);
	}
}