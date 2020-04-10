package springbootdemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springbootdemo.dao.CourseDao;
import springbootdemo.model.Course;
import springbootdemo.model.Topic;


@Service
public class CourseService {
	
	@Autowired
	private CourseDao courseDao;
	
	
	
	public List<Course> getAllCourses(String topicId)
	{
		List<Course> courses=new ArrayList<>();
		courseDao.findByTopicId(topicId).forEach(courses::add);
		return courses;
		
	}
	
	public Optional<Course> getCourse(String id)
	{
		//return topics.stream().filter(t->t.getId().equals(id)).findFirst().get();
		return courseDao.findById(id);
		//topicDao.
	}
	
	public void addCourse(Course course)
	{
		courseDao.save(course);
	}
	
	public void updateCourse(Course course)
	{
		/*for(int i=0;i<topics.size();i++)
		{
			if(topics.get(i).getId().equals(id))
			{
				topics.set(i, topic);
				return;
			}
		}*/
		
		courseDao.save(course);
		
		//topics.add(topic);
	}
	
	public void deleteCourse(String id)
	{
		/*for(int i=0;i<topics.size();i++)
		{
			if(topics.get(i).getId().equals(id))
			{
				topics.remove(i);
			}
		}*/
		courseDao.deleteById(id);
		//topics.removeIf(t->t.getId().equals(id));			
	}

}
