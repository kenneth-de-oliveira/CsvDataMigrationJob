package com.springbatch.datamigration.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.VirtualThreadTaskExecutor;

@Configuration
@RequiredArgsConstructor
public class CsvDataMigrationJobConfig {

	private final JobRepository jobRepository;

	@Bean
	public Job csvDataMigrationJob(
			@Qualifier("studentMigrationStep") 		Step studentMigrationStep	,
			@Qualifier("bookMigrationStep") 		Step bookMigrationStep		) {
		
		return new JobBuilder("csvDataMigrationJob", jobRepository)
				.start( parallelRun(studentMigrationStep, bookMigrationStep))
				.end()
				.incrementer(new RunIdIncrementer())
				.build();
		
	}

	private Flow parallelRun(Step studentMigrationStep, Step bookMigrationStep	) {
		
		var bookMigrationStepFlow = new FlowBuilder<Flow>("bookMigrationStepFlow")
				.start(bookMigrationStep)
				.build();
		
		return new FlowBuilder<Flow>("parallelRun")
				.start(studentMigrationStep)
				.split(new VirtualThreadTaskExecutor())
				.add(bookMigrationStepFlow)
				.build();
	}
	
}