package com.movie.movieservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.movieservice.entity.Movie;

@Repository
public interface MovieDao extends JpaRepository<Movie, Long> {

}
