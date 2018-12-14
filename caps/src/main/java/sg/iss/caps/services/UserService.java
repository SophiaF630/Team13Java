package sg.iss.caps.services;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Optional;
=======
>>>>>>> testing

import sg.iss.caps.model.User;

public interface UserService {
	User findUserByID(String userID);

	User createUser(User user);

	User updateUser(User user);

	void removeUser(User user);
<<<<<<< HEAD
	
	User authenticate(String userID, String password);
	
=======

>>>>>>> testing
	ArrayList<User> findAllUserByType(String type);
}
