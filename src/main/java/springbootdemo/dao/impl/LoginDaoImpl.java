package springbootdemo.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import springbootdemo.dao.LoginDao;
import springbootdemo.model.User;

@Repository
public class LoginDaoImpl implements LoginDao{
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public boolean isValidUser(User user) {
		
		/*Query query=session.getCurrentSession().createQuery("select count(*) from User where username=:uname and password=:pwd");
		query.setParameter("uname", user.getUsername());
		query.setParameter("pwd", user.getPassword());
		//int result=
		List result=query.getResultList();
		
		System.out.println(result);
		System.out.println("hello");
		System.out.println(result.get(0).toString());
		if((result.get(0)).toString().equals("1"))
			return true;
		return false;*/
		
		int userCount=jdbcTemplate.queryForObject("select count(*) from User where username=? and password=?",
				new Object[] {user.getUsername(),user.getPassword()},Integer.class);
		if(userCount==1)
			return true;
		return false;
		
		
		
		
	}

}
