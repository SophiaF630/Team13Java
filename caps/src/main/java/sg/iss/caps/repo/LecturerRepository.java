package sg.iss.caps.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import sg.iss.caps.model.Lecturer;

public interface LecturerRepository extends JpaRepository<Lecturer, String>, JpaSpecificationExecutor<Lecturer>  {

}
