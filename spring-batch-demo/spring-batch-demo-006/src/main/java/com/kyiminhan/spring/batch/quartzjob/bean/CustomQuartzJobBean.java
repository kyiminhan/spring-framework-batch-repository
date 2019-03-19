package com.kyiminhan.spring.batch.quartzjob.bean;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.quartz.QuartzJobBean;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

/**
 * The Class CustomQuartzJobBean.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @since 2019/03/19 </BR>
 *        spring-batch-demo-006 system </BR>
 *        com.kyiminhan.spring.batch.quartzjob.bean </BR>
 *        CustomQuartzJobBean.java </BR>
 */
@Getter

/**
 * Sets the job locator.
 *
 * @param jobLocator the new job locator
 */
@Setter

/** The Constant log. */
@Log4j2
public class CustomQuartzJobBean extends QuartzJobBean {

	/** The job name. */
	private String jobName;

	/** The job launcher. */
	private JobLauncher jobLauncher;

	/** The job locator. */
	private JobLocator jobLocator;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.
	 * quartz.JobExecutionContext)
	 */
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			final Job job = this.jobLocator.getJob(this.jobName);
			final JobParameters params = new JobParametersBuilder()
					.addString("JobID : spring-batch-demo-006 - CustomQuartzJobBean ",
							String.valueOf(System.currentTimeMillis()))
					.toJobParameters();

			this.jobLauncher.run(job, params);
			CustomQuartzJobBean.log.info("\n");
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}