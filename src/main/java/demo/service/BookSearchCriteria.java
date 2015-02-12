package demo.service;

import java.io.Serializable;

import org.springframework.util.StringUtils;

public class BookSearchCriteria implements Serializable {
	private static final long serialVersionUID = -2919384314176135945L;

	private String title;

	private Integer publicationYear;

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPublicationYear(Integer publicationYear) {
		this.publicationYear = publicationYear;
	}

	public String getTitle() {
		return title;
	}

	public Integer getPublicationYear() {
		return publicationYear;
	}

	public boolean openSearch() {
		return hasNoTitle() && hasNoPublicationYear();
	}

	public boolean hasOnlyPublicationYear() {
		return hasPublicationYear() && hasNoTitle();
	}

	public boolean hasOnlyTitle() {
		return hasTitle() && hasNoPublicationYear();
	}

	private boolean hasNoPublicationYear() {
		return !hasPublicationYear();
	}

	public boolean hasPublicationYear() {
		return publicationYear != null;
	}

	public boolean hasTitle() {
		return StringUtils.hasText(title);
	}

	private boolean hasNoTitle() {
		return !hasTitle();
	}
}
