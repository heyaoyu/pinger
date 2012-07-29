package org.hyy.mns.models;

/**
 * @author yaoyu.he
 * 
 */

import java.util.Date;

public class RequestResult {

	private Check check;
	private long rrid;
	private int status;
	private String statusCode;
	private Date ts;
	private boolean success;
	private int responseTime;
	private int failedTimes;
	
	public long getRrid() {
		return rrid;
	}

	public void setRrid(long rrid) {
		this.rrid = rrid;
	}

	public Check getCheck() {
		return check;
	}

	public void setCheck(Check check) {
		this.check = check;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Date getTs() {
		return ts;
	}

	public void setTs(Date ts) {
		this.ts = ts;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public int getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(int responseTime) {
		this.responseTime = responseTime;
	}
	
	public int getFailedTimes() {
		return failedTimes;
	}

	public void setFailedTimes(int failedTimes) {
		this.failedTimes = failedTimes;
	}
	
}
