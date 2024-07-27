package br.com.jorge.library.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.jorge.library.model.Book;
import br.com.jorge.library.repository.BookRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private ObjectMapper objectMapper;

	private Book book;

	@BeforeEach
	void setUp() {
		bookRepository.deleteAll();
		book = new Book(null, "Title", "Author", LocalDate.now(), "123-456-789");
		bookRepository.save(book);
	}

	@Test
	void testCreateBook() throws Exception {
		Book newBook = new Book(null, "New Title", "New Author", LocalDate.now(), "987-654-321");
		mockMvc.perform(post("/api/books").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(newBook))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.title").value(newBook.getTitle()));
	}

	@Test
	void testGetBookById() throws Exception {
		mockMvc.perform(get("/api/books/" + book.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.title").value(book.getTitle()));
	}

	@Test
	void testUpdateBook() throws Exception {
		book.setTitle("Updated Title");
		mockMvc.perform(put("/api/books/" + book.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(book))).andExpect(status().isOk())
				.andExpect(jsonPath("$.title").value("Updated Title"));
	}

	@Test
	void testDeleteBook() throws Exception {
		mockMvc.perform(delete("/api/books/" + book.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

}
