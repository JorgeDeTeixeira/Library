package br.com.jorge.library.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jorge.library.model.Book;
import br.com.jorge.library.service.BookServiceImpl;

@RestController
@RequestMapping("/api/books")
public class BookController {
	@Autowired
	private BookServiceImpl bookService;

	/**
	 * Cria um novo livro.
	 *
	 * @param book O livro a ser criado.
	 * @return A resposta com o livro criado e o status HTTP.
	 */
	@PostMapping
	public ResponseEntity<Book> createBook(@RequestBody Book book) {
		Book createdBook = bookService.save(book);
		return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
	}

	/**
	 * Recupera um livro pelo ID.
	 *
	 * @param id O ID do livro.
	 * @return A resposta com o livro e o status HTTP.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBook(@PathVariable Long id) {
		Optional<Book> book = bookService.findById(id);
		return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	/**
	 * Recupera todos os livros.
	 *
	 * @return A resposta com a lista de livros e o status HTTP.
	 */
	@GetMapping
	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> books = bookService.findAll();
		return ResponseEntity.ok(books);
	}

	/**
	 * Atualiza um livro existente.
	 *
	 * @param id   O ID do livro a ser atualizado.
	 * @param book O livro com os novos dados.
	 * @return A resposta com o livro atualizado e o status HTTP.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
		try {
			Book updatedBook = bookService.update(id, book);
			return ResponseEntity.ok(updatedBook);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	/**
	 * Exclui um livro pelo ID.
	 *
	 * @param id O ID do livro a ser excluído.
	 * @return A resposta com o status HTTP.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
		try {
			bookService.delete(id);
			return ResponseEntity.noContent().build();
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
