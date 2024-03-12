package com.movie.movieservice.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.movie.movieservice.dao.MovieDao;
import com.movie.movieservice.entity.Movie;
import com.movie.movieservice.model.MovieModel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieService {

	private final MovieDao movieDao;
	
	public List<Movie> getAll() {
		return movieDao.findAll();
	}
	
	public Movie getById(Long id) {
		Optional<Movie> data = movieDao.findById(id);
		
		if (data.isPresent()) {
			return data.get();
		}
		
		return null;
	}
	
	public Movie create(MovieModel requestData) {
		Movie data = new Movie();
		data.setTitle(requestData.getTitle());
		data.setDescription(requestData.getDescription());
		data.setRating(requestData.getRating());
		data.setImage(requestData.getImage());
		data.setCreatedAt(new Date());
		
		movieDao.save(data);
		log.info("Success save movie " + data.getId());
		
		Optional<Movie> respMovie = movieDao.findById(data.getId());
		
		if (respMovie.isPresent()) {
			return respMovie.get();
		}
		
		return null;
	}
	
	public Movie update(Long id, MovieModel requestData) {
		Optional<Movie> getData = movieDao.findById(id);
		
		if (getData.isPresent()) {
			Movie data = new Movie();
			data.setId(id);
			data.setTitle(requestData.getTitle());
			data.setDescription(requestData.getDescription());
			data.setRating(requestData.getRating());
			data.setImage(requestData.getImage());
			data.setCreatedAt(getData.get().getCreatedAt());
			data.setUpdatedAt(new Date());
			
			movieDao.save(data);
			log.info("Success update data " + id);
			
			return data;
		}
		
		return null;
	}
	
	public boolean delete(Long id) {
		Optional<Movie> getData = movieDao.findById(id);
		
		if (getData.isPresent()) {
			movieDao.deleteById(id);
			
			return true;
		}
		
		return false;
	}
}
