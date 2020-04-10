package springbootdemo.dao;

import org.springframework.data.repository.CrudRepository;

import springbootdemo.model.Topic;

public interface TopicDao extends CrudRepository<Topic, String>{

}
