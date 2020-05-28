package springbootdemo.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import springbootdemo.dao.AddBookDao;
import springbootdemo.mapper.BookDetailsRowMapper;
import springbootdemo.mapper.BookRowMapper;
import springbootdemo.model.Book;
import springbootdemo.model.BookDetails;

@Repository
public class AddBookDaoImpl implements AddBookDao {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public int addBook(String file, String author, String title) {
		try {
			System.out.println("FILE WHICH IS PASSED::"+file);
			InputStream inputStream =new FileInputStream(new File(file));
			return jdbcTemplate.update("INSERT INTO BOOKS(AUTHOR,TITLE,COVERPIC) VALUES(?,?,?) ",
					author,title,inputStream );
			
		} catch (FileNotFoundException e) {
			System.out.println("ERROR OCCURRED WHILE ADDING FILE TO DB..");
		}
		return 0;
	}

	@Override
	public int addBook1(InputStream file, String author, String title) {
		System.out.println("FILE WHICH IS PASSED::"+file);

		return jdbcTemplate.update("INSERT INTO BOOKS(AUTHOR,TITLE,COVERPIC) VALUES(?,?,?) ",
				author,title,file );
		
	}

	@Override
	public List<BookDetails> getBooks() {
		List<BookDetails> books;
		books=jdbcTemplate.query("select id,author,title,coverPic from Books",  new BookDetailsRowMapper());
		return books;
	}

	@Override
	public int addImage(String name, String type, byte[] picByte) {
		return jdbcTemplate.update("INSERT INTO image_model(name,type,picbyte) VALUES(?,?,?) ",
				name,type,picByte );
	}

}
