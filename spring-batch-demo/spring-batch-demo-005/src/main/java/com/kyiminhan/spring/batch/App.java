package com.kyiminhan.spring.batch;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lombok.extern.log4j.Log4j2;

/**
 * The Class App.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @since Mar 19, 2019 </BR>
 *        spring-batch-demo-004 system </BR>
 *        com.kyiminhan.spring.batch </BR>
 *        App.java </BR>
 */
@Log4j2
public class App {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(final String[] args) {
		new App().perform();
	}

	/**
	 * Perform.
	 */
	public void perform() {
		@SuppressWarnings("resource")
		final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.kyiminhan.spring.batch");
		context.refresh();
		App.log.info("Done");
	}
}