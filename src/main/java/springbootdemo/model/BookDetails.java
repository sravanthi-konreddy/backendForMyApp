package springbootdemo.model;

import java.io.File;
import java.io.OutputStream;
import java.sql.Blob;

import org.springframework.web.multipart.MultipartFile;

public class BookDetails {
	int id;
	String author;
	String title;
	//OutputStream cover;
	//MultipartFile mpf;
	String base64Image;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return
	 */
	public String getAuthor() {
		return author;
	}
	
	public String getBase64Image() {
		return base64Image;
	}
	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	

}
