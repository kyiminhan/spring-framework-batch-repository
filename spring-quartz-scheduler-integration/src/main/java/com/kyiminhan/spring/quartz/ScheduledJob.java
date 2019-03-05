package com.kyiminhan.spring.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.kyiminhan.spring.scheduling.AnotherBean;

/**
 * The Class ScheduledJob.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @since Mar 6, 2019 </BR>
 *        spring-quartz-scheduler-integration system </BR>
 *        com.kyiminhan.spring.quartz </BR>
 *        ScheduledJob.java </BR>
 */
public class ScheduledJob extends QuartzJobBean {

	private AnotherBean anotherBean;

	@Override
	protected void executeInternal(final JobExecutionContext arg0) throws JobExecutionException {
		this.anotherBean.printAnotherMessage();
	}

	public void setAnotherBean(final AnotherBean anotherBean) {
		this.anotherBean = anotherBean;
	}

}