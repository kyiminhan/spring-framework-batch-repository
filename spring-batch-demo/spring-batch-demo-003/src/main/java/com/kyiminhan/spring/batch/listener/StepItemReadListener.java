package com.kyiminhan.spring.batch.listener;

import org.springframework.batch.core.ItemReadListener;

import lombok.extern.log4j.Log4j2;

/**
 * The listener interface for receiving stepItemRead events.
 * The class that is interested in processing a stepItemRead
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addStepItemReadListener<code> method. When
 * the stepItemRead event occurs, that object's appropriate
 * method is invoked.
 *
 * @see StepItemReadEvent
 */

/** The Constant log. */
@Log4j2
public class StepItemReadListener implements ItemReadListener<String> {

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.batch.core.ItemReadListener#beforeRead()
	 */
	@Override
	public void beforeRead() {
		StepItemReadListener.log.info("***** ItemReadListener - beforeRead *****");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.batch.core.ItemReadListener#afterRead(java.lang.Object)
	 */
	@Override
	public void afterRead(String item) {
		StepItemReadListener.log.info("***** ItemReadListener - afterRead *****");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.batch.core.ItemReadListener#onReadError(java.lang.
	 * Exception)
	 */
	@Override
	public void onReadError(Exception ex) {
		StepItemReadListener.log.info("***** ItemReadListener - onReadError *****");
	}
}