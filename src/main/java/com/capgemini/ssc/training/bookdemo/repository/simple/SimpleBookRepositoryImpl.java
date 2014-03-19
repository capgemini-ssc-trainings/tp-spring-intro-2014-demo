package com.capgemini.ssc.training.bookdemo.repository.simple;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import com.capgemini.ssc.training.bookdemo.model.Book;
import com.capgemini.ssc.training.bookdemo.repository.BookRepository;

/**
 * Simple implementation of {@link BookRepository} using {@link ConcurrentMap}
 * to store the data.
 */
@Qualifier("simpleBookRepository")
@Repository
public class SimpleBookRepositoryImpl implements BookRepository {

    // constants

    private final static Comparator<Book> sComparator = new Comparator<Book>() {

	public int compare(Book o1, Book o2) {
	    return o1.getId().compareTo(o2.getId());
	}
    };

    // static data

    private static ConcurrentMap<Integer, Book> sBooksById = new ConcurrentHashMap<>();
    private static ConcurrentMap<Integer, List<Book>> sBooksByPublicationYear = new ConcurrentHashMap<>();
    private static ConcurrentMap<String, List<Book>> sBooksByTitle = new ConcurrentHashMap<>();

    // implementation of BookRepository

    /**
     * {@inheritDoc}
     */
    public void save(Book book) throws DataAccessException {
	if (null == book.getId()) {
	    generateId(book);
	}

	storeBook(book);
    }

    /**
     * {@inheritDoc}
     */
    public Collection<Book> findAll() {
	return sBooksById.values();
    }

    /**
     * {@inheritDoc}
     */
    public Book findById(Integer id) throws DataAccessException {
	if (!sBooksById.containsKey(id)) {
	    throw new DataRetrievalFailureException("No book found with id "
		    + id);
	}
	return sBooksById.get(id);
    }

    /**
     * {@inheritDoc}
     */
    public Collection<Book> findByPublicationYear(int publicationYear)
	    throws DataAccessException {
	if (!sBooksByPublicationYear.containsKey(publicationYear)) {
	    return Collections.emptyList();
	}
	return sBooksByPublicationYear.get(publicationYear);
    }

    /**
     * {@inheritDoc}
     */
    public Collection<Book> findByTitle(String title)
	    throws DataAccessException {
	if (!sBooksByTitle.containsKey(title)) {
	    return Collections.emptyList();
	}
	return sBooksByTitle.get(title);
    }

    // private

    private static synchronized void storeBook(Book book) {
	sBooksById.put(book.getId(), book);

	if (!sBooksByPublicationYear.containsValue(book.getPublicationYear())) {
	    sBooksByPublicationYear.put(book.getPublicationYear(),
		    new CopyOnWriteArrayList<Book>());
	}

	List<Book> books = sBooksByPublicationYear.get(book
		.getPublicationYear());

	int position = Collections.binarySearch(books, book, sComparator);
	if (position > -1) {
	    books.remove(position);
	}
	books.add(book);
	Collections.sort(books, sComparator);

	if (!sBooksByTitle.containsValue(book.getTitle())) {
	    sBooksByTitle
		    .put(book.getTitle(), new CopyOnWriteArrayList<Book>());
	}

	books = sBooksByTitle.get(book.getTitle());

	position = Collections.binarySearch(books, book, sComparator);
	if (position > -1) {
	    books.remove(position);
	}
	books.add(book);
	Collections.sort(books, sComparator);

    }

    @SuppressWarnings("serial")
    private void generateId(Book book) {
	try {
	    Field idField = Book.class.getDeclaredField("id");
	    idField.setAccessible(true);
	    idField.set(book, UUID.randomUUID().hashCode());
	} catch (IllegalArgumentException | IllegalAccessException
		| NoSuchFieldException | SecurityException e) {
	    throw new DataAccessException(
		    "Error occurred while generating the id", e) {
	    };
	}
    }
}
