package sg.iss.caps.repo;

import java.util.ArrayList;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.iss.caps.model.Course;

public interface CourseRepository extends JpaRepository<Course, String> {

	@Query(value = "select * from Course c where faculty = ?1", nativeQuery = true)
	public ArrayList<Course> findAllSelectedCourse(String faculty);
}
