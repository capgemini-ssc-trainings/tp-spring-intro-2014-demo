package com.capgemini.ssc.training.bookdemo.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.capgemini.ssc.training.bookdemo.model.Book;
import com.capgemini.ssc.training.bookdemo.repository.BookRepository;

public class BookServiceImpl implements BookService {

    // instance data

    @Autowired
    BookRepository bookRepository;

    // public

    /**
     * Sets the instance of {@link BookRepository}
     * 
     * @param bookRepository
     *            the instance of {@link BookRepository}
     */
    public void setBookRepository(BookRepository bookRepository) {
	this.bookRepository = bookRepository;
    }

    // implementation of BookService

    /**
     * {@inheritDoc}
     */
    public void save(Book book) throws DataAccessException {
	bookRepository.save(book);
    }

    /**
     * {@inheritDoc}
     */
    public Collection<Book> findAll() {
	return bookRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    public Book findById(Integer id) throws DataAccessException {
	return bookRepository.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    public Collection<Book> findByPublicationYear(int publicationYear)
	    throws DataAccessException {
	return bookRepository.findByPublicationYear(publicationYear);
    }

    /**
     * {@inheritDoc}
     */
    public Collection<Book> findByTitle(String title)
	    throws DataAccessException {
	return bookRepository.findByTitle(title);
    }
}
