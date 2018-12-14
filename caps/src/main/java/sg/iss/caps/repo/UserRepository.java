package sg.iss.caps.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.iss.caps.model.User;

public interface UserRepository extends JpaRepository<User, String>{
	@Query(value="select * from User where userID = ?1 and password = ?2", nativeQuery = true)
	User findUserByNamePwd(@Param("un") String userID, @Param("pwd") String password);

	@Query(value="select * from User where UserType =?1", nativeQuery = true)
	ArrayList<User> findAllUserByType(String type);
}
