package br.com.jorge.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jorge.library.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	/**
	 * Encontra um livro pelo seu ISBN.
	 * 
	 * @param isbn ISBN do livro.
	 * @return Livro correspondente ao ISBN, se existir.
	 */
	Book findByIsbn(String isbn);

	/**
	 * Encontra livros por autor.
	 * 
	 * @param author Autor dos livros.
	 * @return Lista de livros correspondentes ao autor.
	 */
	List<Book> findByAuthor(String author);
}
