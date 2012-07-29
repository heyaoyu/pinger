package org.hyy.mns.core;

import java.util.Date;

import org.apache.log4j.Logger;
import org.hyy.mns.jobs.MonitorJob;
import org.hyy.mns.models.Check;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;

public class CheckJobManager {

	private static final Logger LOG = Logger.getLogger(CheckJobManager.class);

	private Scheduler scheduler = null;

	private static CheckJobManager jobMgr = null;

	public static CheckJobManager newInstance() {
		if (jobMgr == null) {
			jobMgr = new CheckJobManager();
		}
		return jobMgr;
	}

	private CheckJobManager() {
		SchedulerFactory sf = new StdSchedulerFactory();
		try {
			scheduler = sf.getScheduler();
			scheduler.start();
		} catch (SchedulerException e) {
			LOG.warn(e.getMessage(), e);
		}
	}

	public void scheduleMonitorJob(Check check) {
		String jobName = "job-" + check.getCid() + check.getUrl();
		String triggerName = "trigger-" + check.getCid() + check.getUrl();
		JobDetail job = new JobDetail(jobName, "monitorgroup", MonitorJob.class);
		job.getJobDataMap().put("check", check);
		Trigger trigger = TriggerUtils.makeSecondlyTrigger(check.getFrequency() * 60);// frequency is
		trigger.setName(triggerName);
		trigger.setGroup("triggergroup");
		trigger.setStartTime(new Date());
		try {
			LOG.warn("Schedule Job: " + job.getName() + " using Trigger: " + trigger.getName());
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			LOG.warn(e.getMessage(), e);
		}
	}

	public void removeMonitorJob(Check check) {
		String jobName = "job-" + check.getCid() + check.getUrl();
		String triggerName = "trigger-" + check.getCid() + check.getUrl();
		try {
			scheduler.pauseTrigger(triggerName, "triggergroup");
			scheduler.unscheduleJob(triggerName, "triggergroup");
			scheduler.deleteJob(jobName, "monitorgroup");
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	public void rescheduleMonitorJob(Check check) {
		removeMonitorJob(check);
		scheduleMonitorJob(check);
	}

	public void stopScheduler() {
		try {
			scheduler.shutdown();
		} catch (SchedulerException e) {
			LOG.warn(e.getMessage(), e);
		}
	}

}