package br.com.jorge.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.jorge.library.exception.BookNotFoundException;
import br.com.jorge.library.model.Book;
import br.com.jorge.library.repository.BookRepository;

public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public Book save(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public Optional<Book> findById(Long id) {
		return bookRepository.findById(id);
	}

	@Override
	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	@Override
	public Book update(Long id, Book book) {
		if (bookRepository.existsById(id)) {

			Book existingBook = bookRepository.findById(id)
					.orElseThrow(() -> new BookNotFoundException("Book not found with id " + id));

			existingBook.setTitle(book.getTitle());
			existingBook.setAuthor(book.getAuthor());
			existingBook.setPublishedDate(book.getPublishedDate());
			existingBook.setIsbn(book.getIsbn());

			return bookRepository.save(book);
		} else {
			throw new BookNotFoundException("Book not found with id " + id);
		}
	}

	@Override
	public void delete(Long id) {
		if (bookRepository.existsById(id)) {
			bookRepository.deleteById(id);
		} else {
			throw new BookNotFoundException("Book not found with id " + id);
		}

	}

}
