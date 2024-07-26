package br.com.jorge.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jorge.library.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	
}
