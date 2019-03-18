package com.kyiminhan.spring.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.kyiminhan.spring.batch.Tasklet.FirstBatch;

import lombok.Setter;

/**
 * The Class BatchConfiguration.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @since 2019/03/18 </BR>
 *        spring-batch-demo-001 system </BR>
 *        com.kyiminhan.spring.batch.config </BR>
 *        BatchConfiguration.java </BR>
 */
@Configuration
@EnableBatchProcessing
@ComponentScan(basePackages = { "com.kyiminhan" })

/**
 * Sets the step builder factory.
 *
 * @param stepBuilderFactory the new step builder factory
 */
@Setter(onMethod = @__(@Autowired))
public class BatchConfig {

	/** The job builder factoryl. */
	private JobBuilderFactory jobBuilderFactoryl;

	/** The step builder factory. */
	private StepBuilderFactory stepBuilderFactory;

	/**
	 * Transaction manager.
	 *
	 * @return PlatformTransactionManager
	 */
	@Bean
	public PlatformTransactionManager transactionManager() {
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
		return new MapJobRepositoryFactoryBean(this.transactionManager()).getObject();
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
	 * Step 1.
	 *
	 * @return Step
	 */
	@Bean
	public Step step1() {
		return this.stepBuilderFactory.get("step1").tasklet(new FirstBatch()).build();
	}

	/**
	 * Hellwo world job.
	 *
	 * @return Job
	 */
	@Bean("job")
	public Job hellwoWorldJob() {
		return this.jobBuilderFactoryl.get("helloWorldJob").start(this.step1()).build();
	}
}