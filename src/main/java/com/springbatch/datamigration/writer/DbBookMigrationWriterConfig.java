package com.springbatch.datamigration.writer;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.datamigration.domain.Book;

@Configuration
public class DbBookMigrationWriterConfig {

	@Bean
	public JdbcBatchItemWriter<Book> bookMigrationWriter(
			@Qualifier("appDataSource") DataSource dataSource) {

		return new JdbcBatchItemWriterBuilder<Book>()
				.dataSource(dataSource)
				.sql("INSERT INTO book (id, title, authorName, text, isbn, createdAt, updatedAt) VALUES (:id, :title, :authorName, :text, :isbn, :createdAt, :updatedAt)")
				.beanMapped()
				.build();

	}

}