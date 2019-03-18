package com.kyiminhan.spring.batch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import lombok.extern.log4j.Log4j2;

/**
 * The listener interface for receiving jobResult events.
 * The class that is interested in processing a jobResult
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addJobResultListener<code> method. When
 * the jobResult event occurs, that object's appropriate
 * method is invoked.
 *
 * @see JobResultEvent
 */

/** The Constant log. */
@Log4j2
public class JobResultListener implements JobExecutionListener {

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.batch.core.JobExecutionListener#beforeJob(org.
	 * springframework.batch.core.JobExecution)
	 */
	@Override
	public void beforeJob(JobExecution jobExecution) {
		JobResultListener.log.info("***** Called beforeJob(). *****");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.batch.core.JobExecutionListener#afterJob(org.
	 * springframework.batch.core.JobExecution)
	 */
	@Override
	public void afterJob(JobExecution jobExecution) {
		JobResultListener.log.info("***** Called afterJob(). *****");
	}
}