package com.thinkpalm.moviecatalogservice.resources;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thinkpalm.moviecatalogservice.models.CatalogItem;

@RestController
@RequestMapping("catalog")
public class MovieCatalogResource {
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String id){
		return Collections.singletonList(
				new CatalogItem("Transformers","test", 4)
		);
	}
}
