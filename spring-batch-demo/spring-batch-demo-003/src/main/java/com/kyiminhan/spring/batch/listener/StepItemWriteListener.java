package com.kyiminhan.spring.batch.listener;

import java.util.List;

import org.springframework.batch.core.ItemWriteListener;

import lombok.extern.log4j.Log4j2;

/**
 * The listener interface for receiving stepItemWrite events.
 * The class that is interested in processing a stepItemWrite
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addStepItemWriteListener<code> method. When
 * the stepItemWrite event occurs, that object's appropriate
 * method is invoked.
 *
 * @see StepItemWriteEvent
 */

/** The Constant log. */
@Log4j2
public class StepItemWriteListener implements ItemWriteListener<Number> {

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.batch.core.ItemWriteListener#beforeWrite(java.util.List)
	 */
	@Override
	public void beforeWrite(List<? extends Number> items) {
		StepItemWriteListener.log.info("***** ItemWriteListener - beforeWrite *****");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.batch.core.ItemWriteListener#afterWrite(java.util.List)
	 */
	@Override
	public void afterWrite(List<? extends Number> items) {
		StepItemWriteListener.log.info("***** ItemWriteListener - afterWrite *****");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.batch.core.ItemWriteListener#onWriteError(java.lang.
	 * Exception, java.util.List)
	 */
	@Override
	public void onWriteError(Exception exception, List<? extends Number> items) {
		StepItemWriteListener.log.info("***** ItemWriteListener - onWriteError *****");
	}
}