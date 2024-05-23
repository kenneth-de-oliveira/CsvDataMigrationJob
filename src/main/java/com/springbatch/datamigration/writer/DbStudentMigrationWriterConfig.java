package com.springbatch.datamigration.writer;

import com.springbatch.datamigration.domain.Student;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DbStudentMigrationWriterConfig {

	@Bean
	public JdbcBatchItemWriter<Student> studentMigrationWriter(
			@Qualifier("appDataSource") DataSource dataSource) {

		return new JdbcBatchItemWriterBuilder<Student>()
				.dataSource(dataSource)
				.sql("INSERT INTO student (id, document, name, email) VALUES (?, ?, ?, ?)")
				.itemPreparedStatementSetter(preparedStatementSetter())
				.build();
	}

	private ItemPreparedStatementSetter<Student> preparedStatementSetter() {

		return (student, ps) -> {
			ps.setInt(1, student.getId());
			ps.setString(2, student.getDocument());
			ps.setString(3, student.getName());
			ps.setString(4, student.getEmail());
		};

	}

}