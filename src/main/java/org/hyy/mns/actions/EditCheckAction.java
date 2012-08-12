package org.hyy.mns.actions;

import org.hyy.mns.core.CheckJobManager;
import org.hyy.mns.daos.CheckDAO;
import org.hyy.mns.models.*;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionSupport;

public class EditCheckAction extends ActionSupport {

	public String execute() {
		User user = (User) ActionContext.getContext().getSession().get("LOGINUSER");
		String checkId = ((String[]) ActionContext.getContext().getParameters().get("checkId"))[0];
		String checkName = ((String[]) ActionContext.getContext().getParameters().get("checkname"))[0];
		String checkUrl = ((String[]) ActionContext.getContext().getParameters().get("checkurl"))[0];
		String frequency = ((String[]) ActionContext.getContext().getParameters().get("checkFrequency"))[0];
		String needMonitor = null;
		if (ActionContext.getContext().getParameters().get("needMonitor") != null) {
			needMonitor = ((String[]) ActionContext.getContext().getParameters().get("needMonitor"))[0];
		}
		int frequencyNum = Integer.parseInt(frequency);
		String limit = ((String[]) ActionContext.getContext().getParameters().get("checklimit"))[0];
		int limitNum = Integer.parseInt(limit);
		String notifiesStr = ((String[])ActionContext.getContext().getParameters().get("notifies"))[0];
		String[] notifies = notifiesStr.split(";");
		Check thisCheck = CheckDAO.newInstance().getCheck(Long.parseLong(checkId));
		thisCheck.setUser(user);
		thisCheck.setName(checkName);
		if(!thisCheck.getUrl().equals(checkUrl)){
			CheckJobManager.newInstance().removeMonitorJob(thisCheck);
			thisCheck.setUrl(checkUrl);
			CheckJobManager.newInstance().scheduleMonitorJob(thisCheck);
		}else{
			thisCheck.setUrl(checkUrl);
		}
		thisCheck.setFrequency(frequencyNum);
		thisCheck.setTimesLimit(limitNum);
		thisCheck.getNotifies().clear();
		for(String notify : notifies){
			if(notify.length()>0)
				thisCheck.getNotifies().add(notify.trim());
		}
		if (needMonitor != null) {
			thisCheck.setNeedMonitor(true);
			CheckJobManager.newInstance().rescheduleMonitorJob(thisCheck);
			user.getUrlChecks().remove(thisCheck);
			user.getUrlChecks().add(thisCheck);
		} else {
			thisCheck.setNeedMonitor(false);
			CheckJobManager.newInstance().removeMonitorJob(thisCheck);
			user.getUrlChecks().remove(thisCheck);
			ActionContext.getContext().getSession().put("LOGINUSER", user);
		}
		CheckDAO.newInstance().saveCheck(thisCheck);
		return ActionSupport.SUCCESS;
	}
}
