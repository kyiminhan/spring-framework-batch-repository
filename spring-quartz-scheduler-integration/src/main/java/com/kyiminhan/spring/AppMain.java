package com.kyiminhan.spring;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The Class AppMain.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @since Mar 6, 2019 </BR>
 *        spring-quartz-scheduler-integration system </BR>
 *        com.kyiminhan.spring </BR>
 *        AppMain.java </BR>
 */
public class AppMain {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	@SuppressWarnings({ "unused", "resource" })
	public static void main(final String[] args) {
		final AbstractApplicationContext context = new ClassPathXmlApplicationContext("quartz-context.xml");
	}

}
