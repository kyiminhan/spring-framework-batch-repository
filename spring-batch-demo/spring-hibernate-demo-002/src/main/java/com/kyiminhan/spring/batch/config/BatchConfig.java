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
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.kyiminhan.spring.batch.Tasklet.MyTaskOne;
import com.kyiminhan.spring.batch.Tasklet.MyTaskTwo;

import lombok.Setter;

/**
 * The Class BatchConfig.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @since 2019/03/18 </BR>
 *        spring-hibernate-demo-002 system </BR>
 *        com.kyiminhan.spring.batch.config </BR>
 *        BatchConfig.java </BR>
 */
@Configuration
@EnableBatchProcessing
@ComponentScan(basePackages = { "com.kyinminhan.spring" })

/**
 * Sets the step builder factory.
 *
 * @param stepBuilderFactory the new step builder factory
 */
@Setter(onMethod = @__(@Autowired))
public class BatchConfig {

	/** The job builder factory. */
	private JobBuilderFactory jobBuilderFactory;

	/** The step builder factory. */
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

	/**
	 * Step one.
	 *
	 * @return Step
	 */
	@Bean
	public Step stepOne() {
		return this.stepBuilderFactory.get("setp1").tasklet(new MyTaskOne()).build();
	}

	/**
	 * Step two.
	 *
	 * @return Step
	 */
	@Bean
	public Step stepTwo() {
		return this.stepBuilderFactory.get("setp2").tasklet(new MyTaskTwo()).build();
	}

	/**
	 * Demo job.
	 *
	 * @return Job
	 */
	@Bean("job")
	public Job demoJob() {
		return this.jobBuilderFactory.get("demoJob").incrementer(new RunIdIncrementer()).start(this.stepOne())
				.next(this.stepTwo()).build();
	}
}