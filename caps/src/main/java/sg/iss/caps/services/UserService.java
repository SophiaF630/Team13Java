package sg.iss.caps.services;

import sg.iss.caps.model.User;

public interface UserService {
	User findUserByID(String userID);

	User createUser(User user);

	User updateStudent(User user);

	void removeUser(User user);
}
