package com.kyiminhan.spring.batch.config;

import java.io.IOException;
import java.util.Properties;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.kyiminhan.spring.batch.quartzjob.bean.CustomQuartzJobBean;

/**
 * The Class QuartzConfig.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @since 2019/03/20 </BR>
 *        spring-batch-demo-007 system </BR>
 *        com.kyiminhan.spring.batch.config </BR>
 *        QuartzConfig.java </BR>
 */
@Configuration
public class QuartzConfig {

	/**
	 * Job registry bean post processor.
	 *
	 * @param jobRegistry the job registry
	 * @return JobRegistryBeanPostProcessor
	 */
	@Bean
	public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry) {
		final JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
		jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
		return jobRegistryBeanPostProcessor;
	}

	/**
	 * Job one detail.
	 *
	 * @return JobDetail
	 */
	@Bean
	public JobDetail jobOneDetail() {
		// Set Job data map
		final JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("jobName", "demoJobOne");

		return JobBuilder.newJob(CustomQuartzJobBean.class).withIdentity("demoJobOne", null).setJobData(jobDataMap)
				.storeDurably().build();
	}

	/**
	 * Job two detail.
	 *
	 * @return JobDetail
	 */
	@Bean
	public JobDetail jobTwoDetail() {
		// Set Job data map
		final JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("jobName", "demoJobTwo");

		return JobBuilder.newJob(CustomQuartzJobBean.class).withIdentity("demoJobTwo", null).setJobData(jobDataMap)
				.storeDurably().build();
	}

	/**
	 * Job one trigger.
	 *
	 * @return Trigger
	 */
	@Bean
	public Trigger jobOneTrigger() {
		final SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10)
				.repeatForever();

		return TriggerBuilder.newTrigger().forJob(this.jobOneDetail()).withIdentity("jobOneTrigger", null)
				.withSchedule(scheduleBuilder).build();
	}

	/**
	 * Job two trigger.
	 *
	 * @return Trigger
	 */
	@Bean
	public Trigger jobTwoTrigger() {
		final SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(20)
				.repeatForever();

		return TriggerBuilder.newTrigger().forJob(this.jobTwoDetail()).withIdentity("jobTwoTrigger", null)
				.withSchedule(scheduleBuilder).build();
	}

	/**
	 * Scheduler factory bean.
	 *
	 * @return SchedulerFactoryBean
	 * @throws IOException        Signals that an I/O exception has occurred.
	 * @throws SchedulerException the scheduler exception
	 */
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() throws IOException, SchedulerException {
		final SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
		scheduler.setTriggers(this.jobOneTrigger(), this.jobTwoTrigger());
		scheduler.setQuartzProperties(this.quartzProperties());
		scheduler.setJobDetails(this.jobOneDetail(), this.jobTwoDetail());
		scheduler.setApplicationContextSchedulerContextKey("applicationContext");
		return scheduler;
	}

	/**
	 * Quartz properties.
	 *
	 * @return Properties
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Properties quartzProperties() throws IOException {
		final PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
		propertiesFactoryBean.afterPropertiesSet();
		return propertiesFactoryBean.getObject();
	}
}