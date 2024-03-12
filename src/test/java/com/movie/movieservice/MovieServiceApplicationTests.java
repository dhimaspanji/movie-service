package com.movie.movieservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.movieservice.model.MovieModel;

@SpringBootTest
@AutoConfigureMockMvc
class MovieServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	void getAll() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/movie"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void getById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/movie/1"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void create() throws Exception {
		MovieModel data = createMovie();
		String dataString = objectMapper.writeValueAsString(data);
		mockMvc.perform(MockMvcRequestBuilders.post("/movie")
				.contentType(MediaType.APPLICATION_JSON)
				.content(dataString))
		.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	void update() throws Exception {
		MovieModel data = updateMovie();
		String dataString = objectMapper.writeValueAsString(data);
		mockMvc.perform(MockMvcRequestBuilders.post("/movie/2")
				.contentType(MediaType.APPLICATION_JSON)
				.content(dataString))
		.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	void delete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/movie/3"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	private MovieModel createMovie() {
		MovieModel data = MovieModel.builder()
				.title("Pengabdi Ketan")
				.description("Ketan susu enak")
				.rating((float) 10)
				.image("https://images.genpi.co/uploads/arsip/normal/2021/11/26/ketan-susu-keju-foto-cookpad-hcod.jpg")
				.build();
		
		return data;
	}
	
	private MovieModel updateMovie() {
		MovieModel data = MovieModel.builder()
				.title("Penggoda Setan")
				.description("Manusia laknat yang suka menggoda syaiton")
				.rating((float) 1.0)
				.image("https://th.bing.com/th?id=OIP.Poxqm90_uvD1D-mJ8tE2jQHaEK&w=333&h=187&c=8&rs=1&qlt=90&o=6&pid=3.1&rm=2")
				.build();		
		
		return data;
	}

}
