package demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import demo.model.Book;
import demo.repository.BookRepository;
import demo.service.BookSearchCriteria;
import demo.service.BookService;

@RestController
public class BookRestService {

	@Autowired
	BookService service;

	@RequestMapping(value = "books", method = RequestMethod.GET)
	@ResponseBody
	List<Book> hello(BookSearchCriteria searchCriteria) {
		return service.find(searchCriteria);

	}
	@RequestMapping(value = "books/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Book get(@PathVariable("id") Long id) {
		return service.read(id);
	}
	
	@RequestMapping(value = "books/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "books", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> saveOrUpdate(@RequestBody Book book) {
		service.saveOrUpdate(book);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
