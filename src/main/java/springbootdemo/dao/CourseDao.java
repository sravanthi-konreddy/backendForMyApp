package springbootdemo.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import springbootdemo.model.Course;

public interface CourseDao extends CrudRepository<Course, String>{
	
	/*
	 * you need not provide the implementation by declaring the method as
	 * below..start with findBy..followed by field..if that is not primitive field..
	 * then follow it by primitive field in that object by which you want to get
	 * records
	 */
	public List<Course> findByTopicId(String topicId);
	

}
