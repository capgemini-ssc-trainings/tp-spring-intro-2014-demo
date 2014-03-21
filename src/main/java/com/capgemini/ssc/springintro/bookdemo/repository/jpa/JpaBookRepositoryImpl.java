package com.capgemini.ssc.springintro.bookdemo.repository.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.capgemini.ssc.springintro.bookdemo.model.Book;
import com.capgemini.ssc.springintro.bookdemo.repository.BookRepository;

/**
 * JPA implementation of {@link BookRepository}.
 */
@Qualifier("jpaBookRepository")
@Repository
public class JpaBookRepositoryImpl implements BookRepository {

    // instance data

    @PersistenceContext
    private EntityManager em;

    // implementation of BookRepository

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Book book) throws DataAccessException {
	if (null == book.getId()) {
	    em.persist(book);
	} else {
	    em.merge(book);
	}
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Book> findAll() {
	return em.createQuery("SELECT book FROM Book book ORDER BY book.id",
		Book.class).getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Book findById(Integer id) throws DataAccessException {
	return em.find(Book.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Book> findByPublicationYear(int publicationYear)
	    throws DataAccessException {
	TypedQuery<Book> query = em.createNamedQuery("findByPublicationYear",
		Book.class);
	query.setParameter("publicationYear", publicationYear);
	return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Book> findByTitle(String title)
	    throws DataAccessException {
	TypedQuery<Book> query = em.createNamedQuery("findByTitle", Book.class);
	query.setParameter("title", title + "%");
	return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Integer id) throws DataAccessException {
	Book book = findById(id);
	if (null != book) {
	    em.remove(book);
	}
    }
}
