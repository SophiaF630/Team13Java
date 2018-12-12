package sg.iss.caps.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.iss.caps.model.Student;


public interface StudentRepository extends JpaRepository<Student, String> {
	@Query(value="select * from Student where Student_StudentID = ?1",nativeQuery = true)
	Student findByStudentID(String sid);
	

}
