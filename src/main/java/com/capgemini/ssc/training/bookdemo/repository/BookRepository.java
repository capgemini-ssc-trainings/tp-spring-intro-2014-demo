package com.capgemini.ssc.training.bookdemo.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import com.capgemini.ssc.training.bookdemo.model.Book;

/**
 * Repository class for {@link Book} domain objects
 * <p>
 * All method names are compliant with Spring Data naming conventions so this
 * interface can easily be extended for Spring Data See
 */
public interface BookRepository {

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
    Book findById(int id) throws DataAccessException;
}
