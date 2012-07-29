package org.hyy.mns.jobs;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.hyy.mns.daos.RRDAO;
import org.hyy.mns.models.Check;
import org.hyy.mns.models.RequestResult;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MonitorJob implements Job{

	private static final Logger LOG = Logger.getLogger(MonitorJob.class);
	
	@Override
	public void execute(JobExecutionContext ctxt) throws JobExecutionException {
		JobDataMap dataMap = ctxt.getJobDetail().getJobDataMap();
		Check check = (Check) dataMap.get("check");
		LOG.warn("Accessing url: "+ check.getUrl());
		RequestResult rr = new RequestResult();
		rr.setCheck(check);
		HttpClient http = new HttpClient();
		GetMethod get = new GetMethod(check.getUrl());
		Date start = new Date();
		rr.setTs(start);
		try {
			int status = http.executeMethod(get);
			Date end = new Date();
			LOG.warn(status);
			rr.setSuccess(true);
			rr.setStatus(status);
			rr.setStatusCode(get.getStatusText());
			rr.setResponseTime((int)(end.getTime()-start.getTime()));
		} catch (HttpException e) {
			rr.setSuccess(false);
			LOG.warn(e.getMessage(), e);
		} catch (IOException e) {
			rr.setSuccess(false);
			LOG.warn(e.getMessage(), e);
		}
		RRDAO.newInstance().saveResult(rr);
	}

}
