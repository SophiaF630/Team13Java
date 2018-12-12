package sg.iss.caps.repo;

import java.util.ArrayList;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.iss.caps.model.Course;

public interface CourseRepository extends JpaRepository<Course, String> {

	@Query(value = "select * from Course c where c.faculty = ?1", nativeQuery = true)
	public ArrayList<Course> findAllSelectedCourse(String faculty);
}
