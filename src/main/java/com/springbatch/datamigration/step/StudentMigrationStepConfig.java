package com.springbatch.datamigration.step;

import com.springbatch.datamigration.domain.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class StudentMigrationStepConfig {

	private final JobRepository jobRepository;
	private final PlatformTransactionManager transactionManager;

	@Bean
	public Step studentMigrationStep(

			ItemReader<Student> 						fileStudentMigrationReader			,
			ClassifierCompositeItemWriter<Student>		studentMigrationClassifierWriter	,
			FlatFileItemWriter<Student> 				studentInvalidFileMigrationWriter
	) {

		return new StepBuilder("studentMigrationStep", jobRepository)
				.<Student, Student>chunk(15000, transactionManager)
				.reader(fileStudentMigrationReader)
				.writer(studentMigrationClassifierWriter)
				.stream(studentInvalidFileMigrationWriter)
				.build();
	}

}