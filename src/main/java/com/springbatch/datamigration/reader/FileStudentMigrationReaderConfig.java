package com.springbatch.datamigration.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.springbatch.datamigration.domain.Student;

@Configuration
public class FileStudentMigrationReaderConfig {

	@Bean
	public FlatFileItemReader<Student> fileStudentMigrationReader() {

		return new FlatFileItemReaderBuilder<Student>()
				.name("fileStudentMigrationReader")
				.resource(new FileSystemResource("src/main/resources/student.csv"))
				.delimited()
				.names("id", "document", "name", "email")
				.fieldSetMapper(fieldSetMapper())
				.build();
	}

	private FieldSetMapper<Student> fieldSetMapper() {

		return fieldSet -> {
			var student = new Student();
			student.setId(fieldSet.readInt("id"));
			student.setDocument(fieldSet.readString("document"));
			student.setName(fieldSet.readString("name"));
			student.setEmail(fieldSet.readString("email"));
			return student;
		};

	}

}