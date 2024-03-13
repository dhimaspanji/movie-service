package com.movie.movieservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.movie.movieservice.entity.Movie;
import com.movie.movieservice.model.MovieModel;
import com.movie.movieservice.model.Response;
import com.movie.movieservice.service.MovieService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
@Slf4j
public class MovieController {

	private final MovieService movieService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Response getAll() {
		Response resp = new Response();
		
		try {
			List<Movie> data = movieService.getAll();
			
			if (data.size() > 0) {
				resp.setCode(HttpStatus.OK.value());
				resp.setMessage("Success");
				resp.setData(data);
			} else {
				resp.setCode(HttpStatus.NOT_FOUND.value());
				resp.setMessage("Data not found");
			}
		} catch (Exception e) {
			log.error("Error : ", e);
			
			resp.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			resp.setMessage("Internal server error");
		}
		
		return resp;
	}
	
	@GetMapping("/{keyword}")
	@ResponseStatus(HttpStatus.OK)
	public Response getByKeyword(@PathVariable("keyword") String keyword) {
		Response resp = new Response();
		
		try {
			Object data = movieService.getByKeyword(keyword);
			
			if (data != null) {
				resp.setCode(HttpStatus.OK.value());
				resp.setMessage("Success");
				resp.setData(data);
			} else {
				resp.setCode(HttpStatus.NOT_FOUND.value());
				resp.setMessage("Data not found");
			}
		} catch (Exception e) {
			log.error("Error : ", e);
			
			resp.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			resp.setMessage("Internal server error");
		}
		
		return resp;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Response create(@RequestBody MovieModel requestData) {
		Response resp = new Response();
		
		try {
			Float rating = requestData.getRating();
			
			if (rating > 10.0) {
				resp.setCode(HttpStatus.BAD_REQUEST.value());
				resp.setMessage("Rating must not exceed 10.0");
			} else {
				Movie data = movieService.create(requestData);
				
				if (data != null) {
					resp.setCode(HttpStatus.CREATED.value());
					resp.setMessage("Create success");
					resp.setData(data);
				} else {
					resp.setCode(HttpStatus.NOT_FOUND.value());
					resp.setMessage("Data not found");
				}
			}
		} catch (Exception e) {
			log.error("Error : ", e);
			
			resp.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			resp.setMessage("Internal server error");
		}
		
		return resp;
	}
	
	@PostMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Response update(@PathVariable("id") Long id, @RequestBody MovieModel requestData) {
		Response resp = new Response();
		
		try {
			Float rating = requestData.getRating();
			
			if (rating > 10.0) {
				resp.setCode(HttpStatus.BAD_REQUEST.value());
				resp.setMessage("Rating must not exceed 10.0");
			} else {
				Movie data = movieService.update(id, requestData);
				
				if (data != null) {
					resp.setCode(HttpStatus.CREATED.value());
					resp.setMessage("Update success");
					resp.setData(data);
				} else {
					resp.setCode(HttpStatus.NOT_FOUND.value());
					resp.setMessage("Data not found");
				}
			}
		} catch (Exception e) {
			log.error("Error : ", e);
			
			resp.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			resp.setMessage("Internal server error");
		}
		
		return resp;
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Response delete(@PathVariable("id") Long id) {
		Response resp = new Response();
		
		try {
			boolean isDelete = movieService.delete(id);
			
			if (isDelete) {
				resp.setCode(HttpStatus.OK.value());
				resp.setMessage("Delete success");
			} else {
				resp.setCode(HttpStatus.NOT_FOUND.value());
				resp.setMessage("Data not found");
			}
		} catch (Exception e) {
			log.error("Error : ", e);
			
			resp.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			resp.setMessage("Internal server error");
		}
		
		return resp;
	}
}
