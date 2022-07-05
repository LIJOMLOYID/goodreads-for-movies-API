package com.thinkpalm.ratingsdataservice.models;

import java.util.List;

/**
 * @author Lijo M Loyid
 *
 */

public class UserRating {
	
	private List<Rating> userRating;

	public List<Rating> getUserRating() {
		return userRating;
	}

	public void setUserRating(List<Rating> userRating) {
		this.userRating = userRating;
	}
	

}
