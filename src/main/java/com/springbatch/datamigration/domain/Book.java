package com.springbatch.datamigration.domain;

import lombok.Data;
import org.apache.logging.log4j.util.Strings;

import java.util.Date;

@Data
public class Book {

	private int id;
	private String title;
	private String authorName;
	private String text;
	private String isbn;
	private Date createdAt;
	private Date updatedAt;

	public boolean isValid() {
		return !Strings.isBlank(title) && !Strings.isBlank(authorName) && !Strings.isBlank(text) && !Strings.isBlank(isbn) && createdAt != null && updatedAt != null;
	}

}