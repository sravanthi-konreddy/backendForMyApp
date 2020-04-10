package springbootdemo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springbootdemo.model.Course;
import springbootdemo.model.Topic;
import springbootdemo.service.CourseService;
import springbootdemo.service.TopicService;

@RestController
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@RequestMapping("/topics/{id}/courses")
	public List<Course> getAllCourses(@PathVariable String id)
	{
		return courseService.getAllCourses(id);
	}
	
	@RequestMapping("/topics/{id}/courses/{courseId}")
	public Optional<Course> getCourse(@PathVariable String id,@PathVariable String courseId)
	{
		return courseService.getCourse(id);
		
	}
	@RequestMapping(value="/topics/{tid}/courses",method=RequestMethod.POST)
	public void addCourse(@RequestBody Course course,@PathVariable String tid)
	{
		course.setTopic(new Topic(tid,"",""));
		courseService.addCourse(course);
		
	}
	
	@RequestMapping(value="/topics/{tid}/courses/{cid}",method=RequestMethod.PUT)
	public void updateTopic(@RequestBody Course course,@PathVariable String tid,@PathVariable String cid)
	{
		courseService.updateCourse(course);
	}
	
	@RequestMapping(value="/topics/{tid}/courses/{cid}",method=RequestMethod.DELETE)
	public void deleteTopic(@PathVariable String id)
	{
		courseService.deleteCourse(id);
	}

}
