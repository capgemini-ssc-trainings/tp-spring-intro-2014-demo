package demo.service;

import java.util.List;

import demo.model.Book;

public interface BookService {

  void saveOrUpdate(Book book);

  Book read(Long id);

  void delete(Long id);

  List<Book> find(BookSearchCriteria bookSearchCriteria);
}
