package com.thinkpalm.moviecatalogservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.thinkpalm.moviecatalogservice.models.CatalogItem;
import com.thinkpalm.moviecatalogservice.models.Movie;
import com.thinkpalm.moviecatalogservice.models.Rating;

/**
 * @author Lijo M Loyid
 *
 */

@Service
public class MovieInfo {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod="getFallbackCatalogItem")
	public CatalogItem getCatalogItem(Rating rating) {
		// For each movie ID, call movie info service and get details
		Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"
			+ rating.getMovieId(), Movie.class);			
		// Put them all together
		return new CatalogItem(movie.getName(),  movie.getDescription(), rating.getRating());
	}
	
	public CatalogItem getFallbackCatalogItem(Rating rating) {		
		return new CatalogItem("Movie name not found",  "", rating.getRating());		
	}
}
