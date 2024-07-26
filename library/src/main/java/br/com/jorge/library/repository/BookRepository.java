package br.com.jorge.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jorge.library.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	Optional<Book> findByBookId(Long id);
}
