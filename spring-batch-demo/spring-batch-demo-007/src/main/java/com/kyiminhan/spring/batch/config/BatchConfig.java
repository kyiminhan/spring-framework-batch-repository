package com.kyiminhan.spring.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kyiminhan.spring.batch.tasklet.MyTaskOne;
import com.kyiminhan.spring.batch.tasklet.MyTaskTwo;

import lombok.Setter;

/**
 * The Class BatchConfig.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @since 2019/03/20 </BR>
 *        spring-batch-demo-007 system </BR>
 *        com.kyiminhan.spring.batch.config </BR>
 *        BatchConfig.java </BR>
 */
@Configuration
@EnableBatchProcessing

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
	 * Step one.
	 *
	 * @return Step
	 */
	@Bean
	public Step stepOne() {
		return this.stepBuilderFactory.get("stepOne").tasklet(new MyTaskOne()).build();
	}

	/**
	 * Step two.
	 *
	 * @return Step
	 */
	@Bean
	public Step stepTwo() {
		return this.stepBuilderFactory.get("stepTwo").tasklet(new MyTaskTwo()).build();
	}

	/**
	 * Demo job one.
	 *
	 * @return Job
	 */
	@Bean(name = "jobOneDemo")
	public Job demoJobOne() {
		return this.jobBuilderFactory.get("demoJobOne").start(this.stepOne()).next(this.stepTwo()).build();
	}

	/**
	 * Demo job two.
	 *
	 * @return Job
	 */
	@Bean(name = "jobTwoDemo")
	public Job demoJobTwo() {
		return this.jobBuilderFactory.get("demoJobTwo").flow(this.stepOne()).build().build();
	}
}