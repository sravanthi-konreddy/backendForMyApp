package springbootdemo.dao;

import springbootdemo.model.User;

public interface RegisterUserDao {
	
	public int registerUser(User user);
	
	public boolean checkIfUsernameAlreadyExists(User user);

}
