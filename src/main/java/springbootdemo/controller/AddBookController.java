package springbootdemo.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.ws.rs.FormParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mysql.cj.log.Log;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import springbootdemo.dao.AddBookDao;
import springbootdemo.model.BookDetails;
import springbootdemo.model.BookToUpload;



@RestController
public class AddBookController {
	
	@Autowired
	private AddBookDao addBookDao;
	
	@RequestMapping("/testFileAdd")
	public String welcome()
	{
		int i=addBookDao.addBook("C:\\Users\\Sravanthi\\Desktop\\NSR.png", "NSR", "NSR");
		if(i>0)
			return "SUCCESS IT IS....";
		else
			return "FAILURE IT IS....";
	}
	
	
	/*@RequestMapping(value="/addFile",method=RequestMethod.POST, consumes = "multipart/form-data")
	public ResponseEntity<Boolean> addBook(@RequestParam("file") MultipartFile file)
	{
		try {
			addBookDao.addBook1(file.getInputStream(), "author_test","title_test");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("in getting file!..");
		return ResponseEntity.ok().body(true);
	}*/
	
	@RequestMapping(value="/addFile",method=RequestMethod.POST, consumes = MediaType.ALL_VALUE)
	//public ResponseEntity<Boolean> addBook(@RequestBody BookToUpload fileDetails)
	public @ResponseBody String addBook(@RequestParam("bookTitle") String bookTitle,@RequestParam("bookAuthor") String bookAuthor,@RequestParam("coverPage") MultipartFile file )
	{
		
		
		
		try {
			//System.out.println("IN ADD BOOK..."+fileDetails.getFile().getInputStream());
			System.out.println("title::"+bookTitle);
			System.out.println("author::"+file);
			addBookDao.addBook1(file.getInputStream(), bookAuthor,bookTitle);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("in getting file!..");
		return "Success";
	}
	
	@RequestMapping(value="/getFiles",method=RequestMethod.GET, produces=MediaType.ALL_VALUE)
	public ResponseEntity<List<BookDetails>> getBooks()
	{
		System.out.println("getting files!!..");
		return ResponseEntity.ok().body(addBookDao.getBooks());
	}

}
