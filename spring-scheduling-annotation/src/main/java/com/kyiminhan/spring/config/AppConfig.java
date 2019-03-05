package com.kyiminhan.spring.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * The Class AppConfig.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @since 2019/03/05 </BR>
 *        spring-scheduling-annotation system </BR>
 *        com.kyiminhan.spring </BR>
 *        AppConfig.java </BR>
 */
@Configuration
@EnableScheduling
public class AppConfig implements SchedulingConfigurer {

	/**
	 * My bean.
	 *
	 * @return MyBean
	 */
	@Bean
	public MyBean myBean() {
		return new MyBean();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.scheduling.annotation.SchedulingConfigurer#configureTasks
	 * (org.springframework.scheduling.config.ScheduledTaskRegistrar)
	 */
	@Override
	public void configureTasks(final ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(this.taskExecutor());
	}

	/**
	 * Task executor.
	 *
	 * @return Executor
	 */
	@Bean(destroyMethod = "shutdown")
	public Executor taskExecutor() {
		return Executors.newScheduledThreadPool(10);
	}
}