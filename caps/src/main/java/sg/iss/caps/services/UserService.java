package sg.iss.caps.services;

import java.util.ArrayList;

import sg.iss.caps.model.User;

public interface UserService {
	User findUserByID(String userID);

	User createUser(User user);

	User updateUser(User user);

	void removeUser(User user);

	ArrayList<User> findAllUserByType(String type);
}
