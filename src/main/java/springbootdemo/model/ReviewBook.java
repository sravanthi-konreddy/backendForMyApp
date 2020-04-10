package springbootdemo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="ReviewBook")
public class ReviewBook implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	String username;
	@Id
	private Long id;
	int rating;
	String review;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
