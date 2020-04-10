package springbootdemo.service;

import springbootdemo.model.ReviewBook;

public interface ReviewBookService {
	
	public int insertReviewBook(ReviewBook reviewBook);
	
	public boolean checkReviewExists(ReviewBook reviewBook);
	
	public int updateReviewBook(ReviewBook reviewBook);

}
