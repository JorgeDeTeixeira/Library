package br.com.jorge.library.model;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
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

@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Schema(description = "Details about the book")
public class Book {
	@Schema(description = "Unique identifier of the book", example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Schema(description = "Title of the book", example = "Effective Java")
	@NotBlank(message = "Title não pode ser vazio.")
	private String title;

	@Schema(description = "Author of the book", example = "Joshua Bloch")
	private String author = "Desconhecido";

	@Schema(description = "Date when the book was published", example = "2008-05-28")
	@NotNull(message = "Published Date não pode ser nulo.")
	@PastOrPresent(message = "Published Date não pode ser no futuro.")
	private LocalDate publishedDate;

	@Schema(description = "ISBN of the book", example = "978-0134685991")
	@NotBlank(message = "ISBN não pode ser vazio.")
	private String isbn;
}
