package demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

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

import demo.model.Book;
import demo.service.BookSearchCriteria;
import demo.service.BookService;

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
		// when
		Book book = getBookService().read(1L);
		// then
		assertNotNull(book);
		assertEquals(2007, book.getPublicationYear());
		assertEquals("G. King", book.getAuthors());
		assertEquals("Hibernate in Action", book.getTitle());
	}

	@Test()
	public void findBookByIdFailed() {
		// when
		Book book = getBookService().read(10L);
		// then
		assertNull(book);
	}

	@Test
	public void findBookByPublicationYearSuccessfull() {
		// given
		BookSearchCriteria publicationYear2007 = new BookSearchCriteria();
		publicationYear2007.setPublicationYear(2007);
		// when
		List<Book> books = getBookService().find(publicationYear2007);
		assertEquals(1, books.size());
		// then
		assertEquals(2007, books.get(0).getPublicationYear());
	}

	@Test
	public void findBookByPublicationYearFailed() {
		// given
		BookSearchCriteria publicationYear2077 = new BookSearchCriteria();
		publicationYear2077.setPublicationYear(2077);
		// when
		List<Book> books = getBookService().find(publicationYear2077);
		// then
		assertEquals(0, books.size());
	}

	@Test
	public void findBookByTitleSuccessfull() {
		// given
		BookSearchCriteria hibernatInAction = new BookSearchCriteria();
		hibernatInAction.setTitle("Hibernate in Action");
		// when
		List<Book> books = getBookService().find(hibernatInAction);
		// then
		assertEquals(1, books.size());
		assertEquals("Hibernate in Action", books.get(0).getTitle());
	}

	@Test
	public void findBookByTitleFailed() {
		// given
		BookSearchCriteria noExistingTitle = new BookSearchCriteria();
		noExistingTitle.setTitle("Not existing title");
		// when
		List<Book> books = getBookService().find(noExistingTitle);
		// then
		assertEquals(0, books.size());
	}

	@Test
	public void findBookByTitleAndPublicationYearSuccessfull() {
		// given
		BookSearchCriteria yearAndTitle = new BookSearchCriteria();
		yearAndTitle.setPublicationYear(2007);
		yearAndTitle.setTitle("Hibernate in Action");
		// when
		List<Book> books = getBookService().find(yearAndTitle);
		assertEquals(1, books.size());
		// then
		Book book = books.get(0);
		assertEquals(2007, book.getPublicationYear());
		assertEquals("Hibernate in Action", book.getTitle());
	}

	@Test
	public void createAndDeleteBook() {
		Book newBook = new Book("W.Wheeler, J.White", "Spring in Practice",
				2013);
		getBookService().saveOrUpdate(newBook);

		newBook = getBookService().read(4L);
		assertNotNull(newBook);
		assertEquals(2013, newBook.getPublicationYear());
		assertEquals("W.Wheeler, J.White", newBook.getAuthors());
		assertEquals("Spring in Practice", newBook.getTitle());

		getBookService().delete(4L);
		newBook = getBookService().read(4L);
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
