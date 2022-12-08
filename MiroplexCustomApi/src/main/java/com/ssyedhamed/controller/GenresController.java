package com.ssyedhamed.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssyedhamed.dao.GenresRepository;
import com.ssyedhamed.entities.Genre;
import com.ssyedhamed.entities.Movie;

@RestController
@RequestMapping("/miroplex-api")
@CrossOrigin
public class GenresController {
	@Autowired
	GenresRepository genresDao;
	
	
	@PostMapping("/genres")
	public Genre addGenre(@RequestBody Genre genre) {
		System.out.println(genre);
		return this.genresDao.save(genre);
	}
	@GetMapping("/genres")
	public List<Genre> getGenres(){
		return this.genresDao.findAll();
	}
	@GetMapping("/genres/{id}")
	public ResponseEntity<Genre> getGenre(@PathVariable String id){
		Optional<Genre> opt=this.genresDao.findById(Long.parseLong(id));
		if(opt.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Genre>(opt.get() , HttpStatus.OK);
	}
	@DeleteMapping("/genres/{id}")
	public ResponseEntity<Genre> deleteGenre(@PathVariable String id) {
		
		Optional<Genre> opt=this.genresDao.findById(Long.parseLong(id));
		if(opt.isEmpty()) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		this.genresDao.delete(opt.get());
		return new ResponseEntity<Genre>(HttpStatus.OK);
	}
}
