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

@Getter
@Setter
@Log4j2
public class CustomQuartzJobBean extends QuartzJobBean {

	private String jobName;
	private JobLauncher jobLauncher;
	private JobLocator jobLocator;

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