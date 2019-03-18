package com.kyiminhan.spring.batch.Tasklet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

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
public class FirstBatch implements Tasklet {

	/** The logger. */
	static Logger logger = LogManager.getLogger(FirstBatch.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.batch.core.step.tasklet.Tasklet#execute(org.
	 * springframework.batch.core.StepContribution,
	 * org.springframework.batch.core.scope.context.ChunkContext)
	 */
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		FirstBatch.logger.info("** First Batch Job is Executing! **");
		FirstBatch.logger.info("** Hello World.................! **");
		return RepeatStatus.FINISHED;
	}
}