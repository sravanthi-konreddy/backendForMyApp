package springbootdemo.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springbootdemo.dao.ReviewBookDao;
import springbootdemo.model.ReviewBook;
import springbootdemo.service.ReviewBookService;


@Service
public class ReviewBookServiceImpl implements ReviewBookService {
	
	@Autowired
	ReviewBookDao reviewBookDAO;

	@Override
	public int insertReviewBook(ReviewBook reviewBook) {
		return reviewBookDAO.insertReviewBook(reviewBook);
		//return 0;
	}

	@Override
	public boolean checkReviewExists(ReviewBook reviewBook) {
		// TODO Auto-generated method stub
		return reviewBookDAO.checkReviewExists(reviewBook);
		//return false;
	}

	@Override
	public int updateReviewBook(ReviewBook reviewBook) {
		// TODO Auto-generated method stub
		return reviewBookDAO.updateReviewBook(reviewBook);
		//return 0;
	}

}
