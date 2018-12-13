package sg.iss.caps.services;

import java.util.ArrayList;
import java.util.Optional;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import sg.iss.caps.model.Student;
import sg.iss.caps.model.StudentGrade;
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
	public Studentcourse createStudentCourse(Studentcourse sc) {
		return screpo.saveAndFlush(sc);
	}
	
	@Override
	public Studentcourse updateStudentCourse(Studentcourse sc) {
		return screpo.saveAndFlush(sc);
	}


	@Override
	public StudentcoursePK findStudentcoursePK(String sid, String ci) {
		StudentcoursePK scpk = new StudentcoursePK();
		scpk.setStudent_StudentID(sid);
		scpk.setCourseIndex(ci);
		return scpk;
	}

	@Override
	public double calculateStudentGPA(String sid) {
		ArrayList<Studentcourse> studentcourse = screpo.studentViewGrade(sid, "Finished");
		double total = 0;
		int totalAU = 0;
		double GPA = 0;
		double courseGPA = 0;
		for (Studentcourse sc : studentcourse) {
			
			double grade = sc.getCAGrade() * 0.1 + sc.getExamGrade() * 0.9;
				if (grade >= 85) {
					courseGPA = 5;
				} else if (grade >= 80) {
					courseGPA = 5;
				} else if (grade >= 75) {
					courseGPA = 4.5;
				} else if (grade >= 70) {
					courseGPA = 4;
				} else if (grade >= 65) {
					courseGPA = 3.5;
				} else if (grade >= 60) {
					courseGPA = 3;
				} else if (grade >= 55) {
					courseGPA = 2.5;
				} else if (grade >= 50) {
					courseGPA = 2;
				} else if (grade >= 45) {
					courseGPA = 1.5;
				} else if (grade >= 40) {
					courseGPA = 1;
				} else {
					courseGPA = 0;
				}

				total += courseGPA * sc.getCourse().getCredits();
				totalAU += sc.getCourse().getCredits();
				System.out.println(total);
				System.out.println(totalAU);
		}
		GPA = total / totalAU;
		return GPA;
	}
	
	@Override
	public double calculateStudentCGPA(String sid) {
		ArrayList<Studentcourse> studentcourse = screpo.studentViewGrade(sid, "Finished");
		double total = 0;
		int totalAU = 0;
		double CGPA = 0;
		double courseGPA = 0;
		for (Studentcourse sc : studentcourse) {
			double grade = sc.getCAGrade() * 0.1 + sc.getExamGrade() * 0.9;
				if (grade >= 85) {
					courseGPA = 5;
				} else if (grade >= 80) {
					courseGPA = 5;
				} else if (grade >= 75) {
					courseGPA = 4.5;
				} else if (grade >= 70) {
					courseGPA = 4;
				} else if (grade >= 65) {
					courseGPA = 3.5;
				} else if (grade >= 60) {
					courseGPA = 3;
				} else if (grade >= 55) {
					courseGPA = 2.5;
				} else if (grade >= 50) {
					courseGPA = 2;
				} else if (grade >= 45) {
					courseGPA = 1.5;
				} else if (grade >= 40) {
					courseGPA = 1;
				} else {
					courseGPA = 0;
				}

				total += courseGPA * sc.getCourse().getCredits();
				totalAU += sc.getCourse().getCredits();
				System.out.println(total);
				System.out.println(totalAU);
		}
		CGPA = total / totalAU;
		return CGPA;
	}

	@Override
	public int getStudentCount(String courseIndex) {
		ArrayList<Studentcourse> sc = screpo.findAllStudentCourseByCourseIndexs(courseIndex);
		return sc.size();
	}

	@Override
	public ArrayList<Studentcourse> Viewcoursebycourseindex(String CourseIndex) {
		// TODO Auto-generated method stub
		return (ArrayList<Studentcourse>) screpo.Viewcoursebycourseindex(CourseIndex);
	}

	@Override
	public Optional<Studentcourse> findStudentCourse(StudentcoursePK scPK) {
		// TODO Auto-generated method stub
		return screpo.findById(scPK);
	}
	
	

}
