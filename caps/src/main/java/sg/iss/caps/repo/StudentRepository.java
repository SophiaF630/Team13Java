package sg.iss.caps.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.iss.caps.model.Student;


public interface StudentRepository extends JpaRepository<Student, String> {
 
}
