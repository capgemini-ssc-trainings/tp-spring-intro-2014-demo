package com.capgemini.ssc.training.bookdemo.service;

import javax.inject.Inject;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Integration test using the simple book repository and dependency
 * configuration in xml file.
 * 
 * @see AbstractBookServiceTest AbstractBookServiceTest for more details.
 */
@ContextConfiguration(locations = { "classpath:spring/business-config-simple.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class BookServiceSimpleConfigTest extends AbstractBookServiceTest {
    @Inject
    private BookService bookService;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    protected BookService getBookService() {
	return bookService;
	// return applicationContext.getBean(BookService.class);
    }

}
