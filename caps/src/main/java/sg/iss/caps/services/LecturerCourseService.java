package sg.iss.caps.services;

import java.util.ArrayList;

import sg.iss.caps.model.Lecturercourse;

public interface LecturerCourseService {

	ArrayList<Lecturercourse> ViewcoursebylectureID(String lid);
}