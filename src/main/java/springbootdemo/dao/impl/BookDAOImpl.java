package springbootdemo.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import springbootdemo.dao.BookDAO;
import springbootdemo.mapper.BookRowMapper;
import springbootdemo.model.Book;



@Repository
public class BookDAOImpl implements BookDAO{
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	/*@Autowired
	SessionFactory session;*/
	
	@Override
	public long save(Book book) {
		return jdbcTemplate.update("INSERT INTO USER(title,author) VALUES(?,?) ",
				book.getTitle(),book.getAuthor() );
	}

	@Override
	public Book get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> list() {
	//List<Book> books=session.getCurrentSession().createQuery("from Book").list();
		List<Book> books;
		books=jdbcTemplate.query("select id,author,title from Book", new BookRowMapper());
	return books;
	}

	@Override
	public void update(long id, Book book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

}
