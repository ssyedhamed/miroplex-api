package com.ssyedhamed.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssyedhamed.entities.Genre;

public interface GenresRepository extends JpaRepository<Genre, Long> {

}
