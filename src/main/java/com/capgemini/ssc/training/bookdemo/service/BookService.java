package com.capgemini.ssc.training.bookdemo.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import com.capgemini.ssc.training.bookdemo.model.Book;

/**
 * Service class to manage {@link Book}s
 */
public interface BookService {
	/**
	 * Save a {@link Book} to the data store, either inserting or updating it.
	 * 
	 * @param book
	 *            the {@link Book} to save
	 * @see BaseEntity#isNew
	 */
	void save(Book book) throws DataAccessException;

	/**
	 * Retrieve all {@link Book}s from the data store.
	 * 
	 * @return a {@link Collection} of {@link Book}s
	 */
	Collection<Book> findAll();

	/**
	 * Retrieve a {@link Book} from the data store by id.
	 * 
	 * @param id
	 *            the id to search for
	 * @return the {@link Book} if found
	 * @throws org.springframework.dao.DataRetrievalFailureException
	 *             if not found
	 */
	Book findById(Integer id) throws DataAccessException;

	/**
	 * Retrieve {@link Book}s from the data store by publication year, returning
	 * all books with publication year.
	 * 
	 * @param publicationYear
	 *            Value to search for
	 * @return a {@link Collection} of matching {@link Book}s (or an empty
	 *         {@link Collection} if none found)
	 */
	Collection<Book> findByPublicationYear(int publicationYear)
			throws DataAccessException;

	/**
	 * Retrieve {@link Book}s from the data store by title, returning all books
	 * with title starting with the given parameter.
	 * 
	 * @param title
	 *            Value to search for
	 * @return a {@link Collection} of matching {@link Book}s (or an empty
	 *         {@link Collection} if none found)
	 */
	Collection<Book> findByTitle(String title) throws DataAccessException;
}
