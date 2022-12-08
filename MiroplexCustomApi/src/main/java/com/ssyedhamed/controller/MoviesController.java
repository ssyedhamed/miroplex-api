package com.ssyedhamed.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssyedhamed.dao.GenresRepository;
import com.ssyedhamed.dao.MoviesRepository;
import com.ssyedhamed.entities.Genre;
import com.ssyedhamed.entities.Movie;

@RestController
@RequestMapping("/miroplex-api")
@CrossOrigin
public class MoviesController {
	@Autowired
	MoviesRepository moviesDao;
	@Autowired
	GenresRepository genresDao;
	
	@RequestMapping("/movies")
	public ResponseEntity<List<Genre>> staticGenres(){
		Genre genre1=new Genre(101,"Action");
		Genre genre2=new Genre(102,"Comedy");
		Genre genre3=new Genre(103,"Thriller");
		Genre genre4=new Genre(104,"Horror");
		Genre genre5=new Genre(105,"Kids");
		Genre genre6=new Genre(106,"Sports");
		
//		Movie movie1=new Movie(1210,"Ertugrul Ghazi",genre1,8,9.5);
		List<Genre> genres=List.of(genre1,genre2,genre3,genre4,genre5,genre6);
//		List<Movie> movies=List.of(movie1);
		this.genresDao.saveAll(genres);
//		this.moviesDao.saveAll(movies);
		return new ResponseEntity<>(HttpStatus.OK);		
	}
	
	
	@PostMapping("/movies")
	public Movie addMovie(@RequestBody Movie movie) {
//		this.genresDao.saveAndFlush(movie.getGenre());
		this.moviesDao.save(movie);
		return  movie;
	}
	@GetMapping("/movies")
	public List<Movie> getMovies(){
		return this.moviesDao.findAll();
	}
	@GetMapping("/movies/{id}")
	public ResponseEntity<Movie> getMovie(@PathVariable String id){
		Optional<Movie> movie= this.moviesDao.findById(Long.parseLong(id));
		if(movie.isPresent()) {
			return new ResponseEntity<Movie>(movie.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
		}
		
	}
	@PutMapping("/movies/{id}")
	public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie, @PathVariable String id) {
		Optional<Movie> opt=this.moviesDao.findById(Long.parseLong(id));
		if(opt.isEmpty()) {
			return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
		}
		System.out.println("Movie to be updated: "+movie);
		System.out.println("movie from db with id:"+id+" ==="+opt.get());
		Movie updateMovie=opt.get();
		updateMovie.setTitle(movie.getTitle());
		updateMovie.setDailyRentalRate(movie.getDailyRentalRate());
		updateMovie.setGenre(movie.getGenre());
		updateMovie.setLiked(movie.isLiked());
		updateMovie.setNumberInStock(movie.getNumberInStock());
		updateMovie.setPublishDate(LocalDateTime.now());
		this.moviesDao.save(updateMovie);
		return new ResponseEntity<>(updateMovie,HttpStatus.OK);
	}
	@DeleteMapping("/movies/{id}")
	public ResponseEntity<Movie> deleteMovie(@PathVariable String id){
		
		Optional<Movie> opt=this.moviesDao.findById(Long.parseLong(id));
		if(opt.isEmpty()) {
			return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
		}
		this.moviesDao.delete(opt.get());
		return new ResponseEntity<Movie>(HttpStatus.OK);
	}
	
	
	
}
