package sg.iss.caps.services;

import java.util.Optional;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import sg.iss.caps.model.Student;
import sg.iss.caps.model.User;
import sg.iss.caps.repo.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Resource
	UserRepository urepo;
	@Override
	public User findUserByID(String userID) {
		// TODO Auto-generated method stub
		return urepo.findById(userID).get();
	}

	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		return urepo.saveAndFlush(user);
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return urepo.saveAndFlush(user);
	}

	@Override
	public void removeUser(User user) {
		// TODO Auto-generated method stub
		urepo.delete(user);
	}

	@Override
	public ArrayList<User> findAllUserByType(String type) {
		return (ArrayList<User>) urepo.findAllUserByType(type);
	}
	
	

	@Override
	public User authenticate(String userID, String password) {
		User u = urepo.findUserByNamePwd(userID, password);
		return u;
	}

}
