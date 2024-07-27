package br.com.jorge.library.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representa um livro na biblioteca.
 */
@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
     * O título do livro.
     */
	@NotBlank(message = "Title não pode ser vazio.")
	private String title;

	/**
     * O autor do livro. Valor padrão é "Desconhecido".
     */
	private String author = "Desconhecido";

	/**
     * A data de publicação do livro. Não pode ser no futuro.
     */
	@NotNull(message = "Published Date não pode ser nulo.")
	@PastOrPresent(message = "Published Date não pode ser no futuro.")
	private LocalDate publishedDate;

	/**
     * O ISBN do livro. Não pode ser vazio.
     */
	@NotBlank(message = "ISBN não pode ser vazio.")
	private String isbn;
}
