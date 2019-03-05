package com.kyiminhan.spring.config;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * The Class MyBean.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @since 2019/03/05 </BR>
 *        spring-scheduling-annotation system </BR>
 *        com.kyiminhan.spring </BR>
 *        MyBean.java </BR>
 */
public class MyBean {

	/**
	 * Prints the message.
	 */
	@Scheduled(fixedRate = 5000)
	public void printMessage() {
		System.out.println("I am called by Spring scheduler......... by Kyi Min Han.");
	}
}