package sg.iss.caps.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import sg.iss.caps.model.Lecturercourse;
import sg.iss.caps.model.LecturercoursePK;

public interface LecturerCourseRepository extends JpaRepository<Lecturercourse, LecturercoursePK>{

}