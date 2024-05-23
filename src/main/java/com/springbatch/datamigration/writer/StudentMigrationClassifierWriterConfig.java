package com.springbatch.datamigration.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemWriterBuilder;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.datamigration.domain.Student;

@Configuration
public class StudentMigrationClassifierWriterConfig {

	@Bean
	public ClassifierCompositeItemWriter<Student> studentClassifierWriter(

			JdbcBatchItemWriter<Student> 		studentMigrationWriter,
			FlatFileItemWriter<Student> 		studentInvalidFileMigrationWriter	) {

		return new ClassifierCompositeItemWriterBuilder<Student>()
				.classifier(classifier(studentMigrationWriter, studentInvalidFileMigrationWriter))
				.build();

	}

	private Classifier<Student, ItemWriter<? super Student>> classifier(

			JdbcBatchItemWriter<Student> 		studentMigrationWriter,
			FlatFileItemWriter<Student> 		studentInvalidFileMigrationWriter	) {

		return pessoa -> pessoa.isValid() ? studentMigrationWriter : studentInvalidFileMigrationWriter;

	}

}