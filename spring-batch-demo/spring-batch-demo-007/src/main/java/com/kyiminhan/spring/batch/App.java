package com.kyiminhan.spring.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lombok.extern.log4j.Log4j2;

/**
 * The Class App.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @since 2019/03/20 </BR>
 *        spring-batch-demo-007 system </BR>
 *        com.kyiminhan.spring.batch </BR>
 *        App.java </BR>
 */
@Log4j2
public class App {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(final String[] args) {
		new App().perform();
	}

	/**
	 * Perform.
	 */
	public void perform() {
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.kyiminhan.spring.batch");
		context.refresh();

		final Job jobOneDemo = (Job) context.getBean("jobOneDemo");
		final Job jobTwoDemo = (Job) context.getBean("jobTwoDemo");
		final JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");

		try {

			final JobParameters params = new JobParametersBuilder()
					.addString("JobID-spring-batch-demo-006", String.valueOf(System.currentTimeMillis()))
					.toJobParameters();

			final JobExecution executionOne = jobLauncher.run(jobOneDemo, params);
			final JobExecution executionTwo = jobLauncher.run(jobTwoDemo, params);
			App.log.info("Exit Status : " + executionOne.getStatus());
			App.log.info("Exit Status : " + executionTwo.getStatus());

		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			if (context != null) {
				context = null;
			}
		}
		App.log.info("Done");
		App.log.info("\n");

	}
}