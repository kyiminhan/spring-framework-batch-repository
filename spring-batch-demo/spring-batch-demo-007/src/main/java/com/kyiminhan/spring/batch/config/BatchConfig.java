package com.kyiminhan.spring.batch.config;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kyiminhan.spring.batch.tasklet.MyTaskOne;

import lombok.Setter;

/**
 * The Class BatchConfig.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @since 2019/03/19 </BR>
 *        spring-batch-demo-007 system </BR>
 *        com.kyiminhan.spring.batch.config </BR>
 *        BatchConfig.java </BR>
 */
@Configuration
@EnableBatchProcessing
@Setter(onMethod = @__(@Autowired))
public class BatchConfig {

	private JobBuilderFactory jobBuilderFactory;

	private StepBuilderFactory stepBuilderFactoryl;

	@Bean
	public Step stepOne() {
		return this.stepBuilderFactoryl.get("stepOne").tasklet(new MyTaskOne()).build();
	}



}