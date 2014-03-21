package com.capgemini.ssc.springintro.bookdemo.repository.springdatajpa;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.capgemini.ssc.springintro.bookdemo.model.Book;
import com.capgemini.ssc.springintro.bookdemo.repository.BookRepository;

/**
 * Spring Data JPA specialization of the {@link BookRepository} interface
 */
public interface SpringDataBookRepository extends BookRepository,
	Repository<Book, Integer> {

    /**
     * {@inheritDoc}
     */
    @Override
    @Query(name = "findByTitle")
    public Collection<Book> findByTitle(@Param("title") String title)
	    throws DataAccessException;
}
