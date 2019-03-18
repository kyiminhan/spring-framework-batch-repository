package com.kyiminhan.spring.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;

import com.kyiminhan.spring.batch.model.Employee;
import com.kyiminhan.spring.batch.processor.ValidationProcessor;

import lombok.Setter;

/**
 * The Class BatchConfig.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @since Mar 19, 2019 </BR>
 *        spring-batch-demo-004 system </BR>
 *        com.kyiminhan.spring.batch.config </BR>
 *        BatchConfig.java </BR>
 */
@Configuration
@EnableBatchProcessing
@ComponentScan(basePackages = { "com.kyinminhan.spring" })
public class BatchConfig {

	/** The job builder factory. */
	@Setter(onMethod = @__(@Autowired))
	private JobBuilderFactory jobBuilderFactory;

	/** The step builder factory. */
	@Setter(onMethod = @__(@Autowired))
	private StepBuilderFactory stepBuilderFactory;

	/**
	 * Platform transaction manager.
	 *
	 * @return PlatformTransactionManager
	 */
	@Bean
	public PlatformTransactionManager platformTransactionManager() {
		return new ResourcelessTransactionManager();
	}

	/**
	 * Job repository.
	 *
	 * @return JobRepository
	 * @throws Exception the exception
	 */
	@Bean
	public JobRepository jobRepository() throws Exception {
		// JobRepository jobRepository = new JobRepositoryFactoryBean();
		final JobRepository jobRepository = new MapJobRepositoryFactoryBean(this.platformTransactionManager())
				.getObject();
		return jobRepository;
	}

	/**
	 * Job launcher.
	 *
	 * @return JobLauncher
	 * @throws Exception the exception
	 */
	@Bean("jobLauncher")
	public JobLauncher jobLauncher() throws Exception {
		final SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(this.jobRepository());
		return jobLauncher;
	}

	/** The input resource. */
	@Value("../input/inputData.csv")
	public Resource inputResource;

	/**
	 * Read CSV files job.
	 *
	 * @return Job
	 */
	@Bean("job")
	public Job readCSVFilesJob() {
		return this.jobBuilderFactory.get("demoJob").incrementer(new RunIdIncrementer()).start(this.step1()).build();
	}

	/**
	 * Step one.
	 *
	 * @return Step
	 */
	@Bean
	public Step step1() {
		return this.stepBuilderFactory.get("step1").<Employee, Employee>chunk(1).reader(this.reader())
				.processor(this.processor()).build();
	}

	/**
	 * Processor.
	 *
	 * @return ItemProcessor
	 */
	@Bean
	public ItemProcessor<Employee, Employee> processor() {
		return new ValidationProcessor();
	}

	/**
	 * Reader.
	 *
	 * @return FlatFileItemReader
	 */
	@Bean
	public FlatFileItemReader<Employee> reader() {
		final FlatFileItemReader<Employee> fileItemReader = new FlatFileItemReader<>();
		fileItemReader.setLineMapper(this.lineMapper());
		fileItemReader.setLinesToSkip(1);
		fileItemReader.setResource(this.inputResource);

		return fileItemReader;
	}

	/**
	 * Line mapper.
	 *
	 * @return LineMapper
	 */
	@Bean
	public LineMapper<Employee> lineMapper() {
		final DefaultLineMapper<Employee> defaultLineMapper = new DefaultLineMapper<>();
		final DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		delimitedLineTokenizer.setNames(new String[] { "id", "firstName", "lastName" });
		delimitedLineTokenizer.setIncludedFields(new int[] { 0, 1, 2 });
		final BeanWrapperFieldSetMapper<Employee> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Employee.class);
		defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		return defaultLineMapper;
	}

//	@Bean
//	public ConsoleItemWriter<Employee> writer() {
//		return new ConsoleItemWriter<Employee>();
//	}
}