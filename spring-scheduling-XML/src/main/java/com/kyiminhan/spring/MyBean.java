package com.kyiminhan.spring;

import org.springframework.stereotype.Component;

/**
 * The Class MyBean.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @since 2019/03/05 </BR>
 *        spring-scheduling-XML system </BR>
 *        com.kyiminhan.spring </BR>
 *        MyBean.java </BR>
 */
@Component(value = "myBean")
public class MyBean {

	/**
	 * Prints the message.
	 */
	public void printMessage() {
		System.out.println("I am called by Spring scheduler......... by Kyi Min Han.");
	}
}