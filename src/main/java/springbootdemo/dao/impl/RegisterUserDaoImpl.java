package springbootdemo.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import springbootdemo.dao.RegisterUserDao;
import springbootdemo.model.User;

@Repository
public class RegisterUserDaoImpl implements RegisterUserDao {
	
	
	
	@Autowired
    private JdbcTemplate jdbcTemplate;


	@Override
	public int registerUser(User user) {
		/*Query query=session.getCurrentSession().createQuery("INSERT INTO USER(username,password,cpassword,name) ?,?,?,?");
		query.setParameter(0, user.getUsername());
		query.setParameter(1, user.getPassword());
		query.setParameter(2, user.getCpassword());
		query.setParameter(3, user.getName());
		
		int res=query.executeUpdate();
		System.out.println(res);
		return res;*/
		/*if(session.getCurrentSession().save(user)!=null)
		{
			System.out.println("success insert");
			return 1;
			
		}*/
		
		/*jdbcTemplate.update("INSERT INTO user_spring_boot_demo(is_active,password,roles,username)"
				+ "VALUES(?,?,'ROLE_ADMIN',?)",true,user.getUsername(),user.getPassword());*/
		
		 return jdbcTemplate.update("INSERT INTO USER(username,password,cpassword,name,is_active,roles) VALUES(?,?,?,?,?,?) ",
				user.getUsername(),user.getPassword(),user.getPassword(),user.getName() ,user.isActive(),user.getRoles());
			
		
	}


	@Override
	public boolean checkIfUsernameAlreadyExists(User user) {
		/*System.out.println(user.getUsername());
		Query query=session.getCurrentSession().createQuery("SELECT COUNT(*) FROM User WHERE username=:uname");
		query.setParameter("uname", user.getUsername());
		List result=query.getResultList();
		System.out.println("hello!!!!");
		System.out.println(result.toString()	);
		System.out.println((result.get(0)).toString());
		if(!(result.get(0)).toString().equals("1"))
			return true;
		return false;*/
		int unameCount=jdbcTemplate.queryForObject("SELECT COUNT(*) FROM User WHERE username=?", new Object[]{user.getUsername()},Integer.class);
		
		if(unameCount==1)
		{
			System.out.println("count::::"+unameCount);
			return true;
		}
			
		else
		{
			System.out.println("Count not 1:::"+unameCount);
			return false;
		}
			
		
	}

}
