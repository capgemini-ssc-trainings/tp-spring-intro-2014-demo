package com.capgemini.ssc.training.bookdemo.service;

import javax.inject.Inject;

import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Integration test using the spring-data-jpa profile.
 * 
 * @see AbstractBookServiceTest AbstractBookServiceTest for more details.
 */
@ContextConfiguration(locations = { "classpath:spring/business-config.xml",
	"classpath:spring/tools-config.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("spring-data-jpa")
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class BookServiceSpringDataJpaTest extends AbstractBookServiceTest {
    @Inject
    private BookService bookService;

    @Override
    protected BookService getBookService() {
	return bookService;
	// return applicationContext.getBean(BookService.class);
    }

}
