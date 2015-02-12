package demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>, BookSearchRepository {
}
