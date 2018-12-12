package sg.iss.caps.repo;

import java.util.ArrayList;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.iss.caps.model.Course;
import sg.iss.caps.model.Studentcourse;
import sg.iss.caps.model.StudentcoursePK;

public interface StudentCourseRepository extends JpaRepository<Studentcourse, StudentcoursePK> {
	@Query("select * form StudentCourse where studentID = :studentID")
	ArrayList<Course> findByStudentID(@Param("studentID") String studentID);
}
