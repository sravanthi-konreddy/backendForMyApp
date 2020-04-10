package springbootdemo.service;

import springbootdemo.model.User;

public interface RegisterUserService {
	
	public int registerUser(User user);
	
	public boolean checkIfUsernameAlreadyExists(User user);

}
