package springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springbootdemo.model.ReviewBook;
import springbootdemo.service.ReviewBookService;


@RestController
public class ReviewBookController {
	
	@Autowired
	ReviewBookService reviewBookService;
	
	@RequestMapping(value="/reviewBook",method=RequestMethod.POST)
	
	public ResponseEntity<Boolean> reviewBook(@RequestBody ReviewBook reviewBook)
	{
		System.out.println("controller");
		System.out.println("uname::"+reviewBook.getUsername());
		System.out.println("id"+reviewBook.getId());
		System.out.println("review"+reviewBook.getReview());
		System.out.println("rating"+reviewBook.getRating());
		int reviewSubmitted;
		if(reviewBookService.checkReviewExists(reviewBook))
		{
			reviewSubmitted=reviewBookService.insertReviewBook(reviewBook);
		}
		else
			
		reviewSubmitted=reviewBookService.updateReviewBook(reviewBook);
		
		System.out.println("submittedreview::"+reviewSubmitted);
		
		if(reviewSubmitted>0)
			return ResponseEntity.ok().body(true);
		return ResponseEntity.ok().body(false);
	}

}
