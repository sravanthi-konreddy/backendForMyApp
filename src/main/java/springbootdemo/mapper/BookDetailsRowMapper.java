package springbootdemo.mapper;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.multipart.MultipartFile;



import springbootdemo.model.BookDetails;

public class BookDetailsRowMapper implements RowMapper<BookDetails> {
	

	public BookDetails mapRow(ResultSet rs, int num) throws SQLException {
		BookDetails book=new BookDetails();
		book.setId(rs.getInt("id"));
		book.setTitle(rs.getString("title"));
		book.setAuthor(rs.getString("author"));
		//book.setCover( (File)(InputStream)rs.getBinaryStream("coverPic")));
		Blob imageBlob = rs.getBlob("coverPic");
		//InputStream binaryStream = imageBlob.getBinaryStream(0, imageBlob.length());
		InputStream inputStream = imageBlob.getBinaryStream();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int bytesRead = -1;
		 
		try {
			while ((bytesRead = inputStream.read(buffer)) != -1) {
			    outputStream.write(buffer, 0, bytesRead);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		byte[] imageBytes = outputStream.toByteArray();
		 
		String base64Image = Base64.getEncoder().encodeToString(imageBytes);
		book.setBase64Image(base64Image);
		return book;
	}

}
