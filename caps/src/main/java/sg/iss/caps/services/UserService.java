package sg.iss.caps.services;

import java.util.ArrayList;
import java.util.Optional;

import sg.iss.caps.model.User;

public interface UserService {
	User findUserByID(String userID);

	User createUser(User user);

	User updateStudent(User user);

	void removeUser(User user);
	
	User authenticate(String userID, String password);
	
	ArrayList<User> findAllUserByType(String type);
}
