package springbootdemo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import springbootdemo.model.User;
import springbootdemo.model.UserSpringBootDemo;

public interface UserRepository extends JpaRepository<User,String> {
	
	List<User> findByUsername(String username);
	

}
