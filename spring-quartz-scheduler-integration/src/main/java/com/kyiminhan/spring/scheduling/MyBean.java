package com.kyiminhan.spring.scheduling;

import org.springframework.stereotype.Component;

/**
 * The Class MyBean.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @since Mar 6, 2019 </BR>
 *        spring-quartz-scheduler-integration system </BR>
 *        com.kyiminhan.spring.scheduling </BR>
 *        MyBean.java </BR>
 */
@Component(value = "myBean")
public class MyBean {

	/**
	 * Prints the message.
	 */
	public void printMessage() {
		System.out.println("I am called by MethodInvokingJobDetailFactoryBean using SimpleTriggerFactoryBean");
	}

}