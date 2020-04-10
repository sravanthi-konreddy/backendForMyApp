package springbootdemo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springbootdemo.dao.TopicDao;
import springbootdemo.model.Topic;

@Service
public class TopicService {
	
	@Autowired
	private TopicDao topicDao;
	
	private  List<Topic> topics=new ArrayList<>(Arrays.asList(new Topic("Spring","Spring Framework","SpringFramework description"),
			new Topic("Angular","Angular 4","Angular Description"),
			new Topic("Java","Java 8","Java description")));
	//topics.add(new Topic("Spring","Spring Framework","SpringFramework description"));
	
	public List<Topic> getAllTopics()
	{
		List<Topic> topics=new ArrayList<>();
		topicDao.findAll().forEach(topics::add);
		return topics;
		
	}
	
	public Optional<Topic> getTopic(String id)
	{
		//return topics.stream().filter(t->t.getId().equals(id)).findFirst().get();
		return topicDao.findById(id);
		//topicDao.
	}
	
	public void addTopic(Topic topic)
	{
		topicDao.save(topic);
	}
	
	public void updateTopic(Topic topic)
	{
		/*for(int i=0;i<topics.size();i++)
		{
			if(topics.get(i).getId().equals(id))
			{
				topics.set(i, topic);
				return;
			}
		}*/
		
		topicDao.save(topic);
		
		//topics.add(topic);
	}
	
	public void deleteTopic(String id)
	{
		/*for(int i=0;i<topics.size();i++)
		{
			if(topics.get(i).getId().equals(id))
			{
				topics.remove(i);
			}
		}*/
		topicDao.deleteById(id);
		//topics.removeIf(t->t.getId().equals(id));			
	}

}
