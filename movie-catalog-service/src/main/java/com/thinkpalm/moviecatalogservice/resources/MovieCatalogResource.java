package com.thinkpalm.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.thinkpalm.moviecatalogservice.models.CatalogItem;
import com.thinkpalm.moviecatalogservice.models.Movie;
import com.thinkpalm.moviecatalogservice.models.Rating;
import com.thinkpalm.moviecatalogservice.models.UserRating;


@RestController
@RequestMapping("catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		
		//RestTemplate restTemplate = new RestTemplate();
		
		// get all rated movie IDs			
		
		//WebClient.Builder builder = WebClient.builder();
		
		UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/"
				+ userId, UserRating.class);
		
		return userRating.getRatings().stream().map(rating-> {
			System.out.println(rating.getRating());
			System.out.println(rating.getMovieId());
			// For each movie ID, call movie info service and get details
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"
				+ rating.getMovieId(), Movie.class);			
			// Put them all together
			return new CatalogItem(movie.getName(),  movie.getDescription(), rating.getRating());
		})
		.collect(Collectors.toList());
		
//		return Collections.singletonList(
//				new CatalogItem("Transformers","test", 4)
//		);
	}
}

/*
Movie movie = webClientBuilder.build()
.get()
.uri("http://localhost:8082/movies/" + rating.getMovieId())
.retrieve()
.bodyToMono(Movie.class)
.block();
*/