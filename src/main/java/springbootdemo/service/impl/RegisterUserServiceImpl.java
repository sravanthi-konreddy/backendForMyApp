package springbootdemo.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springbootdemo.dao.RegisterUserDao;
import springbootdemo.model.User;
import springbootdemo.service.RegisterUserService;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {

	@Autowired
	RegisterUserDao registerUserDao;
	
	@Override
	
	public int registerUser(User user) {
		return registerUserDao.registerUser(user);
	}

	@Override
	
	public boolean checkIfUsernameAlreadyExists(User user) {
		return registerUserDao.checkIfUsernameAlreadyExists(user);
	}

}
