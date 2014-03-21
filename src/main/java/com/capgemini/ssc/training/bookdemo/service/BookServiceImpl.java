package com.capgemini.ssc.training.bookdemo.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.ssc.training.bookdemo.model.Book;
import com.capgemini.ssc.training.bookdemo.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

    // instance data

    @Autowired
    // @Qualifier("jpaBookRepository")
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
    @Transactional()
    @CacheEvict(value="books", allEntries=true)
    public void save(Book book) throws DataAccessException {
	bookRepository.save(book);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Cacheable(value="books")
    public Collection<Book> findAll() {
	return bookRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public Book findById(Integer id) throws DataAccessException {
	return bookRepository.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Cacheable(value="books")
    public Collection<Book> findByPublicationYear(int publicationYear)
	    throws DataAccessException {
	return bookRepository.findByPublicationYear(publicationYear);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Cacheable(value="books")
    public Collection<Book> findByTitle(String title)
	    throws DataAccessException {
	return bookRepository.findByTitle(title);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @CacheEvict(value="books", allEntries=true)
    public void delete(Integer id) throws DataAccessException {
	bookRepository.delete(id);
    }
}
