package springbootdemo.dao;

import springbootdemo.model.ReviewBook;

public interface ReviewBookDao {
	
	public int insertReviewBook(ReviewBook reviewBook);
	
	public boolean checkReviewExists(ReviewBook reviewBook);
	
	public int updateReviewBook(ReviewBook reviewBook);
	
	

}
