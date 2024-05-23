package com.springbatch.datamigration.step;

import com.springbatch.datamigration.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BookMigrationStepConfig {

	private final JobRepository jobRepository;
	private final PlatformTransactionManager transactionManager;

	@Bean
	public Step bookMigrationStep(

			ItemReader<Book> fileBookMigrationReader,
			ItemWriter<Book> bookMigrationWriter

	) {

		return new StepBuilder("bookMigrationStep", jobRepository)
				.<Book, Book>chunk(15000, transactionManager)
				.reader(fileBookMigrationReader)
				.writer(bookMigrationWriter)
				.build();
	}

}