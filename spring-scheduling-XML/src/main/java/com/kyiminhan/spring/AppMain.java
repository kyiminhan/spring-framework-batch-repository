package com.kyiminhan.spring;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The Class AppMain.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @since 2019/03/05 </BR>
 *        spring-scheduling-XML system </BR>
 *        com.kyiminhan.spring </BR>
 *        AppMain.java </BR>
 */
public class AppMain {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(final String... args) {
		@SuppressWarnings({ "unused", "resource" })
		final AbstractApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
	}
}