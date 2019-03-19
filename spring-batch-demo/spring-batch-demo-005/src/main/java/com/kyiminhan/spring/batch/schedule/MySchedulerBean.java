package com.kyiminhan.spring.batch.schedule;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MySchedulerBean {

	@Autowired
	private Job job;

	@Autowired
	private JobLauncher jobLauncher;

	@Scheduled(fixedRate = 10000)
	public void printMessage() throws JobExecutionAlreadyRunningException, JobRestartException,
	JobInstanceAlreadyCompleteException, JobParametersInvalidException {

		MySchedulerBean.log.info("*********** I AM CALLED BY SPRING SCHEDULER MySchedulerBean START ***********");

		final JobParameters params = new JobParametersBuilder()
				.addString("JobID-spring-batch-demo-004", String.valueOf(System.currentTimeMillis())).toJobParameters();

		final JobExecution execution = this.jobLauncher.run(this.job, params);
		MySchedulerBean.log.info("Exit Status : " + execution.getStatus());
		MySchedulerBean.log.info("*********** I AM CALLED BY SPRING SCHEDULER MySchedulerBean END ***********");
		MySchedulerBean.log.info("Done.");
		MySchedulerBean.log.info("\n");
	}
}