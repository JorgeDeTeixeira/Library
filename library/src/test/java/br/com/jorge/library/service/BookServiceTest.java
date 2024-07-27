package br.com.jorge.library.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.jorge.library.exception.BookNotFoundException;
import br.com.jorge.library.model.Book;
import br.com.jorge.library.repository.BookRepository;

public class BookServiceTest {

	@Mock
	private BookRepository bookRepository;

	@InjectMocks
	private BookServiceImpl bookService;

	private Book book;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		book = new Book(1L, "Title", "Author", LocalDate.now(), "123-456-789");
	}

	@Test
	void testSaveBook() {
		when(bookRepository.save(any(Book.class))).thenReturn(book);
		Book savedBook = bookService.save(book);
		assertNotNull(savedBook);
		assertEquals(book.getTitle(), savedBook.getTitle());
	}

	@Test
	void testUpdateBook() {
		when(bookRepository.existsById(book.getId())).thenReturn(true);
		when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
		when(bookRepository.save(any(Book.class))).thenReturn(book);

		Book updatedBook = bookService.update(book.getId(), book);
		assertNotNull(updatedBook);
		assertEquals(book.getTitle(), updatedBook.getTitle());
	}

	@Test
	void testDeleteBook() {
		when(bookRepository.existsById(book.getId())).thenReturn(true);
		bookService.delete(book.getId());
		verify(bookRepository).deleteById(book.getId());
	}

	@Test
	void testBookNotFoundExpcetion() {
		when(bookRepository.findById(book.getId())).thenReturn(Optional.empty());
		try {
			bookService.findById(book.getId());
		} catch (BookNotFoundException e) {
			assertEquals("Book not found with id " + book.getId(), e.getMessage());
		}
	}
}
