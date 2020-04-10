package springbootdemo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import springbootdemo.model.Book;

public class BookRowMapper implements RowMapper<Book> {

	@Override
	public Book mapRow(ResultSet rs, int num) throws SQLException {
		Book book=new Book();
		book.setId(rs.getLong("id"));
		book.setTitle(rs.getString("title"));
		book.setAuthor(rs.getString("author"));
		return book;
	}

}
