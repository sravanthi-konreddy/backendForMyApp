package springbootdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import springbootdemo.model.Book;
import springbootdemo.service.BookService;



@RestController
public class BookController {

	
	@Autowired
	BookService bookService;
	
	/*@Autowired
	BookResponse bookResponse;*/
	
	
	@GetMapping("/book")
	public ResponseEntity<List<Book>> list(){
		List<Book> books=bookService.list();
		/*bookResponse.setBooks(books);
		bookResponse.setResponseCode(200);
		bookResponse.setResponseMessage("Success");
		bookResponse.setResponseStatus("OK");*/
		return ResponseEntity.ok().body(books);
	}
	
	@GetMapping("/api/book1")
	public void list1(){
		System.out.println("HELLO!!!!!");
	}
}
