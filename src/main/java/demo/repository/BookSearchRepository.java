package demo.repository;

import java.util.List;

import demo.model.Book;
import demo.service.BookSearchCriteria;

public interface BookSearchRepository {
	List<Book> find(BookSearchCriteria bookSearchCriteria);
}
