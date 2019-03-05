package com.kyiminhan.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.kyiminhan.spring.config.AppConfig;

/**
 * The Class App.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @since 2019/03/05 </BR>
 *        spring-scheduling-annotation system </BR>
 *        com.kyiminhan.spring </BR>
 *        App.java </BR>
 */
public class App {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(final String... args) {
		@SuppressWarnings({ "unused", "resource" })
		final AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	}
}