package demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.aop.Monitorable;
import demo.model.Book;
import demo.msg.MessagingService;
import demo.repository.BookRepository;

@Transactional
@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	MessagingService msgService;

	@Override
	public void saveOrUpdate(Book book) {
		bookRepository.save(book);
	}

	@Override
	public Book read(Long id) {
		return bookRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		bookRepository.delete(id);
	}

	@Override
	public List<Book> find(BookSearchCriteria bookSearchCriteria) {
		msgService.sendMessage("Books received");
		return bookRepository.find(bookSearchCriteria);
	}
}
