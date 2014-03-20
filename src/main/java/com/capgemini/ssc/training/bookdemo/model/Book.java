package com.capgemini.ssc.training.bookdemo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Simple JavaBean domain object representing a book.
 */
@Entity
@Table(name = "BOOK")
public class Book implements Serializable {

    private static final long serialVersionUID = 5472061873121793580L;

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @Size(max = 200, message = "Maximal 200 characters!")
    @NotEmpty
    @Column(nullable = false, name = "AUTHORS")
    private String authors;

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
     * @param authors
     *            the authors
     * @param title
     *            the title
     * @param publicationYear
     *            the publication year
     */
    public Book(Integer id, String authors, String title, int publicationYear) {
	this(authors, title, publicationYear);
	this.id = id;
    }

    /**
     * Constructor
     * 
     * @param id
     *            the book id
     * @param authors
     *            the authors
     * @param title
     *            the title
     * @param publicationYear
     *            the publication year
     */
    public Book(String authors, String title, int publicationYear) {
	this.authors = authors;
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
     *            the new publication year
     */
    public void setPublicationYear(int publicationYear) {
	this.publicationYear = publicationYear;
    }

    /**
     * Gets the book id
     * 
     * @return the value of the id property
     */
    public Integer getId() {
	return id;
    }

    /**
     * Gets the authors
     * 
     * @return the value of the authors property
     */
    public String getAuthors() {
	return authors;
    }

    /**
     * Sets the author
     * 
     * @param authors
     *            the new authors
     */
    public void setAuthors(String authors) {
	this.authors = authors;
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
