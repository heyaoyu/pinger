package org.hyy.mns.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hyy.mns.daos.RRDAO;

/**
 * @author yaoyu.he
 * 
 * Url Check Class
 * 
 * */

public class Check {

	private User user;
	private long cid;
	private String name;
	private String url;
	private int frequency;// minute
	private int timesLimit;
	private boolean notifyWhenUp=true;
	private boolean needMonitor=true;
	private Set<String> notifies=new HashSet<String>();
	
	public Set<String> getNotifies() {
		return notifies;
	}
	public void setNotifies(Set<String> notifies) {
		this.notifies = notifies;
	}
	public long getCid() {
		return cid;
	}
	public void setCid(long cid) {
		this.cid = cid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public int getTimesLimit() {
		return timesLimit;
	}
	public void setTimesLimit(int timesLimit) {
		this.timesLimit = timesLimit;
	}
	public boolean isNotifyWhenUp() {
		return notifyWhenUp;
	}
	public void setNotifyWhenUp(boolean notifyWhenUp) {
		this.notifyWhenUp = notifyWhenUp;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isNeedMonitor() {
		return needMonitor;
	}
	public void setNeedMonitor(boolean needMonitor) {
		this.needMonitor = needMonitor;
	}
	
	public List<RequestResult> getRequestResults(){
		return RRDAO.newInstance().getResultBy(this);
	}
	
	@Override
	public boolean equals(Object obj) {
	  if (obj instanceof Check){
		  Check o = (Check)obj;
	      return o.getCid() == this.cid;
	   }
	   else
	  {
	      return super.equals(obj);
	  }
	}
	
	@Override
	public int hashCode(){
        return String.valueOf(cid).hashCode();
     }
	
}
