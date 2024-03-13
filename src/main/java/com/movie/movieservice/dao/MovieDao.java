package com.movie.movieservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.movie.movieservice.entity.Movie;

@Repository
public interface MovieDao extends JpaRepository<Movie, Long> {

	@Query(value = "SELECT * FROM tbl_movie WHERE title LIKE CONCAT('%',:title,'%')",
			nativeQuery = true)
	List<Movie> findByTitle(@Param("title") String title);
}
