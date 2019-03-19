package com.kyiminhan.spring.batch.config;

import java.io.IOException;
import java.util.Properties;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.kyiminhan.spring.batch.common.Constant;
import com.kyiminhan.spring.batch.quartzjob.bean.CustomQuartzJobBean;

import lombok.Setter;

/**
 * The Class QuartzConfig.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @since 2019/03/19 </BR>
 *        spring-batch-demo-006 system </BR>
 *        com.kyiminhan.spring.batch.config </BR>
 *        QuartzConfig.java </BR>
 */
@Configuration

/**
 * Sets the job locator.
 *
 * @param jobLocator the new job locator
 */
@Setter(onMethod = @__(@Autowired))
public class QuartzConfig {

	/** The job launcher. */
	private JobLauncher jobLauncher;

	/** The job locator. */
	private JobLocator jobLocator;

	/**
	 * Job registry bean post processor.
	 *
	 * @param jobRegistry the job registry
	 * @return JobRegistryBeanPostProcessor
	 */
	@Bean
	public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry) {
		final JobRegistryBeanPostProcessor beanPostProcessor = new JobRegistryBeanPostProcessor();
		beanPostProcessor.setJobRegistry(jobRegistry);
		return beanPostProcessor;
	}

	/**
	 * Job one detail.
	 *
	 * @return JobDetail
	 */
	@Bean
	public JobDetail jobOneDetail() {
		final JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put(Constant.JOB_NAME, "jobOneDemo");
		jobDataMap.put(Constant.JOB_LAUNCHER, this.jobLauncher);
		jobDataMap.put(Constant.JOB_LOCATOR, this.jobLocator);

		return JobBuilder.newJob(CustomQuartzJobBean.class).withIdentity("jobOneDemo").setJobData(jobDataMap)
				.storeDurably().build();
	}

	/**
	 * Job two detail.
	 *
	 * @return JobDetail
	 */
	@Bean
	public JobDetail jobTwoDetail() {
		final JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put(Constant.JOB_NAME, "jobTwoDemo");
		jobDataMap.put(Constant.JOB_LAUNCHER, this.jobLauncher);
		jobDataMap.put(Constant.JOB_LOCATOR, this.jobLocator);

		return JobBuilder.newJob(CustomQuartzJobBean.class).withIdentity("jobTwoDemo").setJobData(jobDataMap)
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

		return TriggerBuilder.newTrigger().forJob(this.jobOneDetail()).withIdentity("jobOneTrigger")
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

		return TriggerBuilder.newTrigger().forJob(this.jobTwoDetail()).withIdentity("jobTwoTrigger")
				.withSchedule(scheduleBuilder).build();

	}

	/**
	 * Scheduler factory bean.
	 *
	 * @return SchedulerFactoryBean
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() throws IOException {

		final SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
		scheduler.setTriggers(this.jobOneTrigger(), this.jobTwoTrigger());
		scheduler.setQuartzProperties(this.quartzProperties());
		scheduler.setJobDetails(this.jobOneDetail(), this.jobTwoDetail());
		return scheduler;

	}

	/**
	 * Quartz properties.
	 *
	 * @return Properties
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Bean
	public Properties quartzProperties() throws IOException {

		final PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
		propertiesFactoryBean.afterPropertiesSet();
		return propertiesFactoryBean.getObject();

	}
}