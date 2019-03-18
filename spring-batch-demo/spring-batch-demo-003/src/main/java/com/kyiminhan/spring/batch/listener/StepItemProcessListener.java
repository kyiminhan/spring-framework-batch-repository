package com.kyiminhan.spring.batch.listener;

import org.springframework.batch.core.ItemProcessListener;

import lombok.extern.log4j.Log4j2;

/**
 * The listener interface for receiving stepItemProcess events.
 * The class that is interested in processing a stepItemProcess
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addStepItemProcessListener<code> method. When
 * the stepItemProcess event occurs, that object's appropriate
 * method is invoked.
 *
 * @see StepItemProcessEvent
 */

/** The Constant log. */
@Log4j2
public class StepItemProcessListener implements ItemProcessListener<String, Number> {

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.batch.core.ItemProcessListener#beforeProcess(java.lang.
	 * Object)
	 */
	@Override
	public void beforeProcess(String item) {
		StepItemProcessListener.log.info("***** ItemProcessListener - beforeProcess *****");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.batch.core.ItemProcessListener#afterProcess(java.lang.
	 * Object, java.lang.Object)
	 */
	@Override
	public void afterProcess(String item, Number result) {
		StepItemProcessListener.log.info("***** ItemProcessListener - afterProcess *****");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.batch.core.ItemProcessListener#onProcessError(java.lang.
	 * Object, java.lang.Exception)
	 */
	@Override
	public void onProcessError(String item, Exception e) {
		StepItemProcessListener.log.info("***** ItemProcessListener - onProcessError *****");
	}
}