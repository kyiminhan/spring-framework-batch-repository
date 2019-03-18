package com.kyiminhan.spring.batch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.kyiminhan.spring.batch.Tasklet.FirstBatch;

/**
 * The Class App.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @since 2019/03/18 </BR>
 *        spring-batch-demo-001 system </BR>
 *        com.kyiminhan.spring.batch </BR>
 *        App.java </BR>
 */
@EnableBatchProcessing
public class App {

	/** The logger. */
	static Logger logger = LogManager.getLogger(FirstBatch.class);

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.kyiminhan");
		context.refresh();

		final Job job = (Job) context.getBean("job");
		final JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		try {
			final JobExecution execution = jobLauncher.run(job, new JobParameters());
			App.logger.info("Exit Status : " + execution.getStatus());
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			if (context != null) {
				context = null;
			}
		}
		App.logger.info("Done");
	}
}