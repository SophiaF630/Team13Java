package sg.iss.caps.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.iss.caps.model.Studentcourse;
import sg.iss.caps.model.StudentcoursePK;

public interface StudentCourseRepository extends JpaRepository<Studentcourse, StudentcoursePK>{

}
