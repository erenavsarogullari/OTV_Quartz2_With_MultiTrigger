package com.otv.job;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author onlinetechvision.com
 * @since 18 Sept 2011
 * @version 1.0.0
 *
 */
public class FirstTestJob implements Job {

	private Logger log = Logger.getLogger(FirstTestJob.class);
	
	public void execute(JobExecutionContext jExeCtx) throws JobExecutionException {
		log.debug("FirstTestJob triggered by "+jExeCtx.getTrigger().getKey().getName()+" runs successfully.");
	}
	
}
