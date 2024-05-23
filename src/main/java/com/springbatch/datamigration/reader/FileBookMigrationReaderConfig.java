package com.springbatch.datamigration.reader;

import com.springbatch.datamigration.domain.Book;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class FileBookMigrationReaderConfig {

	@Bean
	public FlatFileItemReader<Book> fileBookMigrationReader() {

		return new FlatFileItemReaderBuilder<Book>()
				.name("fileBookMigrationReader")
				.resource(new FileSystemResource("src/main/resources/book.csv"))
				.delimited()
				.names("id", "title", "authorName", "text", "isbn", "createdAt", "updatedAt")
				.fieldSetMapper(fieldSetMapper())
				.build();
	}

	private FieldSetMapper<Book> fieldSetMapper() {

		return fieldSet -> {
			var book = new Book();
			book.setId(fieldSet.readInt("id"));
			book.setTitle(fieldSet.readString("title"));
			book.setAuthorName(fieldSet.readString("authorName"));
			book.setText(fieldSet.readString("text"));
			book.setIsbn(fieldSet.readString("isbn"));
			book.setCreatedAt(fieldSet.readDate("createdAt", "yyyy-MM-dd HH:mm:ss"));
			book.setUpdatedAt(fieldSet.readDate("updatedAt", "yyyy-MM-dd HH:mm:ss"));
			return book;
		};

	}

}