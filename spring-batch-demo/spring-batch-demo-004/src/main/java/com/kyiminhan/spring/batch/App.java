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
 * @since Mar 19, 2019 </BR>
 *        spring-batch-demo-004 system </BR>
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

		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.kyiminhan");
		context.refresh();

		final Job job = (Job) context.getBean("job");
		final JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");

		try {

			final JobParameters params = new JobParametersBuilder()
					.addString("JobID-spring-batch-demo-004", String.valueOf(System.currentTimeMillis()))
					.toJobParameters();

			final JobExecution execution = jobLauncher.run(job, params);
			App.log.info("Exit Status : " + execution.getStatus());

		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			if (context != null) {
				context = null;
			}
		}

		App.log.info("Done");

	}
}