package com.capgemini.ssc.springintro.bookdemo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import com.capgemini.ssc.springintro.bookdemo.model.Book;
import com.capgemini.ssc.springintro.bookdemo.service.BookService;

/**
 * <p>
 * Base class for {@link BookService} integration tests.
 * </p>
 * <p>
 * Subclasses should specify Spring context configuration using
 * {@link ContextConfiguration @ContextConfiguration} annotation
 * </p>
 * <p>
 * AbstractBookServiceTest and its subclasses benefit from the following
 * services provided by the Spring TestContext Framework:
 * </p>
 * <ul>
 * <li><strong>Spring IoC container caching</strong> which spares us unnecessary
 * set up time between test execution.</li>
 * <li><strong>Dependency Injection</strong> of test fixture instances, meaning
 * that we don't need to perform application context lookups. See the use of
 * {@link Autowired @Autowired} on the <code>{@link
 * AbstractBookServiceTest#bookService bookService}</code> instance variable,
 * which uses autowiring <em>by
 * type</em>.
 * <li><strong>Transaction management</strong>, meaning each test method is
 * executed in its own transaction, which is automatically rolled back by
 * default. Thus, even if tests insert or otherwise change database state, there
 * is no need for a teardown or cleanup script.
 * <li>An {@link org.springframework.context.ApplicationContext
 * ApplicationContext} is also inherited and can be used for explicit bean
 * lookup if necessary.</li>
 * </ul>
 */
public abstract class AbstractBookServiceTest {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Rule
    public TestName name = new TestName();

    @Autowired
    private ApplicationContext applicationContext;

    protected abstract BookService getBookService();

    @Test
    public void findBookByIdSuccessfull() {
	Book book = getBookService().findById(1);
	assertNotNull(book);
	assertEquals(2007, book.getPublicationYear());
	assertEquals("G. King", book.getAuthors());
	assertEquals("Hibernate in Action", book.getTitle());
    }

    @Test()
    public void findBookByIdFailed() {
	Book book = getBookService().findById(10);
	assertNull(book);
    }

    @Test()
    public void findAll() {
	Collection<Book> books = getBookService().findAll();
	assertEquals(3, books.size());
    }

    @Test
    public void findBookByPublicationYearSuccessfull() {
	Collection<Book> books = getBookService().findByPublicationYear(2007);
	assertEquals(1, books.size());
	for (Book book : books) {
	    assertEquals(2007, book.getPublicationYear());
	}
    }

    @Test
    public void findBookByPublicationYearFailed() {
	Collection<Book> books = getBookService().findByPublicationYear(2077);
	assertEquals(0, books.size());
    }

    @Test
    public void findBookByTitleSuccessfull() {
	Collection<Book> books = getBookService().findByTitle(
		"Hibernate in Action");
	assertEquals(1, books.size());
	for (Book book : books) {
	    assertEquals("Hibernate in Action", book.getTitle());
	}
    }

    @Test
    public void findBookByTitleFailed() {
	Collection<Book> books = getBookService().findByTitle(
		"Not existing title");
	assertEquals(0, books.size());
    }

    @Test
    public void createAndDeleteBook() {
	Book newBook = new Book("W.Wheeler, J.White", "Spring in Practice",
		2013);
	getBookService().save(newBook);

	newBook = getBookService().findById(4);
	assertNotNull(newBook);
	assertEquals(2013, newBook.getPublicationYear());
	assertEquals("W.Wheeler, J.White", newBook.getAuthors());
	assertEquals("Spring in Practice", newBook.getTitle());

	Collection<Book> books = getBookService().findByPublicationYear(2013);
	assertEquals(1, books.size());
	for (Book book : books) {
	    assertEquals(2013, book.getPublicationYear());
	}

	books = getBookService().findByTitle("Spring in Practice");
	assertEquals(1, books.size());
	for (Book book : books) {
	    assertEquals("Spring in Practice", book.getTitle());
	}

	getBookService().delete(4);
	newBook = getBookService().findById(4);
	assertNull(newBook);
    }

    @Before
    public void before() {
	logger.info("Starting the test method {}", name.getMethodName());
    }

    @After
    public void after() {
	logger.info("The test method {} finished", name.getMethodName());
    }
}