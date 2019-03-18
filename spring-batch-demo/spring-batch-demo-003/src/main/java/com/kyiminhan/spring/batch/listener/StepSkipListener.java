package com.kyiminhan.spring.batch.listener;

import org.springframework.batch.core.SkipListener;

import lombok.extern.log4j.Log4j2;

/**
 * The listener interface for receiving stepSkip events.
 * The class that is interested in processing a stepSkip
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addStepSkipListener<code> method. When
 * the stepSkip event occurs, that object's appropriate
 * method is invoked.
 *
 * @see StepSkipEvent
 */

/** The Constant log. */
@Log4j2
public class StepSkipListener implements SkipListener<String, Number> {

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.batch.core.SkipListener#onSkipInRead(java.lang.Throwable)
	 */
	@Override
	public void onSkipInRead(Throwable t) {
		StepSkipListener.log.info("***** StepSkipListener - onSkipInRead *****");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.batch.core.SkipListener#onSkipInWrite(java.lang.Object,
	 * java.lang.Throwable)
	 */
	@Override
	public void onSkipInWrite(Number item, Throwable t) {
		StepSkipListener.log.info("***** StepSkipListener - afterWrite *****");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.batch.core.SkipListener#onSkipInProcess(java.lang.Object,
	 * java.lang.Throwable)
	 */
	@Override
	public void onSkipInProcess(String item, Throwable t) {
		StepSkipListener.log.info("***** StepSkipListener - onWriteError *****");
	}
}