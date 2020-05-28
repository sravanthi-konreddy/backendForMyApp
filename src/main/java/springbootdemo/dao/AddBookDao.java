package springbootdemo.dao;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import springbootdemo.model.BookDetails;

public interface AddBookDao {
		
		public int addBook(String file,String author, String title);
		
		public int addBook1(InputStream file,String author, String title);
		
		public int addImage(String name,String type,byte[] picByte);
		
		public List<BookDetails> getBooks();
		
}
