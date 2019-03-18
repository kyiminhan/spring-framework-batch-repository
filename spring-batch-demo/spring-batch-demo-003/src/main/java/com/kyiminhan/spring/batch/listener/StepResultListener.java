package com.kyiminhan.spring.batch.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

import lombok.extern.log4j.Log4j2;

/**
 * The listener interface for receiving stepResult events. The class that is
 * interested in processing a stepResult event implements this interface, and
 * the object created with that class is registered with a component using the
 * component's <code>addStepResultListener<code> method. When the stepResult
 * event occurs, that object's appropriate method is invoked.
 *
 * @see StepResultEvent
 */

/** The Constant log. */
@Log4j2
public class StepResultListener implements StepExecutionListener {

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.batch.core.StepExecutionListener#beforeStep(org.
	 * springframework.batch.core.StepExecution)
	 */
	@Override
	public void beforeStep(StepExecution stepExecution) {
		StepResultListener.log.info("***** StepResultListener Called beforeStep(). *****");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.batch.core.StepExecutionListener#afterStep(org.
	 * springframework.batch.core.StepExecution)
	 */
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		StepResultListener.log.info("***** StepResultListener Called afterStep(). *****");
		return ExitStatus.COMPLETED;
	}
}