package com.otv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.otv.job.FirstTestJob;

/**
 * @author onlinetechvision.com
 * @since 18 Sept 2011
 * @version 1.0.0
 *
 */
public class JobsScheduler {
	
	public static void main(String[] args) {
		
		try {
						
			// specify the job' s details..
			JobDetail firstTestJobDetail = JobBuilder.newJob(FirstTestJob.class)
			    .withIdentity("firstTestJob")
			    .build();
			
			// specify the running period of the job
			Trigger firstTrigger = TriggerBuilder.newTrigger()
				  .withIdentity("FirstTrigger")
			      .withSchedule(  
                    SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(15).repeatForever())  
                             .build();  
			
			// specify the running period of the job
			Trigger secondTrigger = TriggerBuilder.newTrigger()
				  .withIdentity("SecondTrigger")
			      .withSchedule(  
                    SimpleScheduleBuilder.simpleSchedule().repeatMinutelyForever())  
                             .build();  
			
			// create trigger list
			List<Trigger> triggerList = new ArrayList<Trigger>();
			triggerList.add(firstTrigger);
			triggerList.add(secondTrigger);
	    	
			// link jobdetail and trigger list
	    	Map<JobDetail,List<Trigger>> map = new HashMap<JobDetail, List<Trigger>>();
	    	map.put(firstTestJobDetail, triggerList);
	    	
	    	//schedule the job
	    	SchedulerFactory schFactory = new StdSchedulerFactory();
	    	Scheduler sch = schFactory.getScheduler();
			sch.start();
	    	sch.scheduleJobs(map, true);
			
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}
