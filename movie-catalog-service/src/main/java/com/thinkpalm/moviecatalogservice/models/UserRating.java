package com.thinkpalm.moviecatalogservice.models;

import java.util.List;

/**
 * @author Lijo M Loyid
 *
 */

public class UserRating {
	
	public UserRating() {

	}

	private List<Rating> userRating;

	public List<Rating> getUserRating() {
		return userRating;
	}

	public void setUserRating(List<Rating> userRating) {
		this.userRating = userRating;
	}
	

}
