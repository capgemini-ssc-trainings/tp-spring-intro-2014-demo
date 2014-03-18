package com.capgemini.ssc.training.bookdemo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Simple JavaBean domain object representing a book.
 */
public class Book implements Serializable {

    private static final long serialVersionUID = 5472061873121793580L;

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @Size(max = 100, message = "Maximal 100 characters!")
    @NotEmpty
    @Column(nullable = false, name = "AUTHOR")
    private String author;

    @Size(max = 30, message = "Maximal 30 characters!")
    @NotEmpty
    @Column(nullable = false, name = "TITLE")
    private String title;

    @Max(value = 2100, message = "To far in the future!")
    @Min(value = 1400, message = "To far in the past!")
    @Column(nullable = false, name = "PUBLICATION_YEAR")
    private int publicationYear;

    /**
     * Constructor
     */
    public Book() {
    }

    /**
     * Constructor
     * 
     * @param id
     *            the book id
     * @param author
     *            the author
     * @param title
     *            the title
     * @param publicationYear
     *            the publication year
     */
    public Book(Long id, String author, String title, int publicationYear) {
	this.id = id;
	this.author = author;
	this.title = title;
	this.publicationYear = publicationYear;
    }

    /**
     * Gets the publication year
     * 
     * @return the value of the publication year property
     */
    public int getPublicationYear() {
	return publicationYear;
    }

    /**
     * Sets the publication year
     * 
     * @param publicationYear
     *            the new publiction year
     */
    public void setPublicationYear(int publicationYear) {
	this.publicationYear = publicationYear;
    }

    /**
     * Gets the book id
     * 
     * @return the value of the id property
     */
    public long getId() {
	return id;
    }

    /**
     * Gets the author
     * 
     * @return the value of the author property
     */
    public String getAuthor() {
	return author;
    }

    /**
     * Sets the author
     * 
     * @param author
     *            the new author
     */
    public void setAuthor(String author) {
	this.author = author;
    }

    /**
     * Gets the title
     * 
     * @return the value of the title property
     */
    public String getTitle() {
	return title;
    }

    /**
     * Sets the title
     * 
     * @param title
     *            the new title
     */
    public void setTitle(String title) {
	this.title = title;
    }

}
