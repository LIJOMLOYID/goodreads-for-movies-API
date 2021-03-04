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

@RestController
@RequestMapping("catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String id){
		
		//RestTemplate restTemplate = new RestTemplate();
		
		// get all rated movie IDs
		
		
		// for each movie ID call, movie info service and get details	
		
		//WebClient.Builder builder = WebClient.builder();
		
		List<Rating> ratings = Arrays.asList(
				new Rating("1234", 4),
				new Rating("5678", 3)
		);
		
		return ratings.stream().map(rating-> {
//			Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"
//				+ rating.getMovieId(), Movie.class);
			
			Movie movie = webClientBuilder.build()
				.get()
				.uri("http://localhost:8082/movies/" + rating.getMovieId())
				.retrieve()
				.bodyToMono(Movie.class)
				.block();
			
			return new CatalogItem(movie.getName(), "Desc", rating.getRating());
		})
		.collect(Collectors.toList());
		
		
		
		// Put them all together
//		return Collections.singletonList(
//				new CatalogItem("Transformers","test", 4)
//		);
	}
}
