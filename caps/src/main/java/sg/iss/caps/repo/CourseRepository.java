package sg.iss.caps.repo;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.iss.caps.model.Course;

public interface CourseRepository extends JpaRepository<Course, String> {

	@Query(value = "select * from Course c where c.endDate > ?1", nativeQuery = true)
	public ArrayList<Course> findAllCurrentSemesterCourse(Date ed);
}
