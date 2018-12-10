package sg.iss.caps.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.iss.caps.model.User;

public interface UserRepository extends JpaRepository<User, String>{

}
