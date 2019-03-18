package com.kyiminhan.spring.batch.Tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import lombok.extern.log4j.Log4j2;

/**
 * The Class FirstBatch.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @since 2019/03/18 </BR>
 *        spring-batch-demo-001 system </BR>
 *        com.kyiminhan.spring.batch.Tasklet </BR>
 *        FirstBatch.java </BR>
 */

/** The Constant log. */
@Log4j2
public class MyTaskOne implements Tasklet {

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.batch.core.step.tasklet.Tasklet#execute(org.
	 * springframework.batch.core.StepContribution,
	 * org.springframework.batch.core.scope.context.ChunkContext)
	 */
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		MyTaskOne.log.info("***** My Task One is Executing! START *****");

		MyTaskOne.log.info("***** My Task One is Executing! END *****");
		return RepeatStatus.FINISHED;
	}
}