package com.springbatch.datamigration.writer;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.springbatch.datamigration.domain.Student;

@Configuration
public class StudentInvalidFileMigrationWriterConfig {

	@Bean
	public FlatFileItemWriter<Student> studentInvalidFileMigrationWriter() {

		return new FlatFileItemWriterBuilder<Student>()
				.name("studentInvalidFileMigrationWriter")
				.resource(new FileSystemResource("src/main/resources/invalid_students.csv"))
				.delimited()
				.names("id", "document", "name", "email")
				.build();

	}

}