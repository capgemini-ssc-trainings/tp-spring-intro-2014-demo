package com.capgemini.ssc.springintro.bookdemo.service;

import javax.inject.Inject;

import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.ssc.springintro.bookdemo.service.BookService;

/**
 * Integration test using the simple book repository and dependency
 * configuration in xml file.
 * 
 * @see AbstractBookServiceTest AbstractBookServiceTest for more details.
 */
@ContextConfiguration(locations = { "classpath:spring/business-config-simple.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class BookServiceSimpleConfigTest extends AbstractBookServiceTest {
    @Inject
    private BookService bookService;

    @Override
    protected BookService getBookService() {
	return bookService;
	// return applicationContext.getBean(BookService.class);
    }

}
