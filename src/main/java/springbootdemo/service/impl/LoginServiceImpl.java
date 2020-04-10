package springbootdemo.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springbootdemo.dao.LoginDao;
import springbootdemo.model.User;
import springbootdemo.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	LoginDao loginDao;

	@Override
	public boolean isValidUser(User user) {
		// TODO Auto-generated method stub
		return loginDao.isValidUser(user);
	}

}
