package com.kyiminhan.spring.batch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import lombok.extern.log4j.Log4j2;

/**
 * The Class MyTaskTwo.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @since 2019/03/19 </BR>
 *        spring-batch-demo-006 system </BR>
 *        com.kyiminhan.spring.batch.tasklet </BR>
 *        MyTaskTwo.java </BR>
 */
@Log4j2
public class MyTaskTwo implements Tasklet {

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.batch.core.step.tasklet.Tasklet#execute(org.
	 * springframework.batch.core.StepContribution,
	 * org.springframework.batch.core.scope.context.ChunkContext)
	 */
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		MyTaskTwo.log.info("***** My Task Two is Executing! START *****");
		MyTaskTwo.log.info("***** Executing");
		MyTaskTwo.log.info("***** My Task Two is Executing! END *****");
		return RepeatStatus.FINISHED;
	}
}