package com.ssyedhamed.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssyedhamed.entities.Movie;

public interface MoviesRepository extends JpaRepository<Movie, Long>{

}
