package br.com.jorge.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.jorge.library.model.Book;

@Service
public interface BookService {
	Book save(Book book);

	Optional<Book> findById(Long id);

	List<Book> findAll();

	Book update(Long id, Book book);

	void delete(Long id);
}
