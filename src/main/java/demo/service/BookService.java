package demo.service;

import java.util.List;

import demo.aop.Monitorable;
import demo.model.Book;


public interface BookService {
	@Monitorable
    void saveOrUpdate(Book book);
    
	@Monitorable
    Book read(Long id);
    
	@Monitorable
    void delete(Long id);
    
	@Monitorable
    List<Book> find(BookSearchCriteria bookSearchCriteria);
}
