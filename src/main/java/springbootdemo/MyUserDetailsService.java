package springbootdemo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import springbootdemo.dao.UserRepository;
import springbootdemo.model.UserSpringBootDemo;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("User name::::"+username);
		List<UserSpringBootDemo>user=userRepository.findByUsername(username);
		System.out.println("size:::"+user.size());
		String roles=null;
		if(user.size()>0)
		{
			
			//user.get(0).setRoles("");
			for(UserSpringBootDemo i:user)
			{
				System.out.println(i.getRoles());
				if(roles!=null)
				 roles=roles+","+i.getRoles();
				else
					roles=i.getRoles();
			}
			
		}
		if(user.size()>0)
		{
		UserSpringBootDemo user1=user.get(0);
		user1.setRoles(roles);
		System.out.println("Roles for user:::"+user1.getRoles());
		return (new MyUserDetails(user1));
		
		}
		else
		{
			throw new UsernameNotFoundException("User not found::"+username);
		}
		
	}

}
