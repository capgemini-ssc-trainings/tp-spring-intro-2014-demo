package demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Book implements Serializable {
	private static final long serialVersionUID = 7591474657266902346L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String authors;

	private String title;

	private int publicationYear;
	
	// for Hibernate
	protected Book() {
	}


	public Book(Long id, String authors, String title, int publicationYear) {
		this(authors, title, publicationYear);
		this.id = id;
	}

	public Book(String authors, String title, int publicationYear) {
		this.authors = authors;
		this.title = title;
		this.publicationYear = publicationYear;
	}

	public Long getId() {
		return id;
	}
	
	public String getAuthors() {
		return authors;
	}

	public String getTitle() {
		return title;
	}

	public int getPublicationYear() {
		return publicationYear;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authors == null) ? 0 : authors.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + publicationYear;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (authors == null) {
			if (other.authors != null)
				return false;
		} else if (!authors.equals(other.authors))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (publicationYear != other.publicationYear)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}
