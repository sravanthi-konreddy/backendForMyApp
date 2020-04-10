package springbootdemo.dao;

import springbootdemo.model.User;

public interface LoginDao {
	
	public boolean isValidUser(User user);

}
