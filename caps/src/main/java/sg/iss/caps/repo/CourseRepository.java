package sg.iss.caps.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.iss.caps.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
