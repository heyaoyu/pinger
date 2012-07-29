package org.hyy.mns.actions;

import org.hyy.mns.core.CheckJobManager;
import org.hyy.mns.daos.CheckDAO;
import org.hyy.mns.models.*;

import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionSupport;

public class AddCheckAction extends ActionSupport {

	public String execute() {
		User user = (User) ActionContext.getContext().getSession().get("LOGINUSER");
		String checkName = ((String[]) ActionContext.getContext().getParameters().get("checkname"))[0];
		String checkUrl = ((String[]) ActionContext.getContext().getParameters().get("checkurl"))[0];
		String frequency = ((String[]) ActionContext.getContext().getParameters().get("checkFrequency"))[0];
		int frequencyNum = Integer.parseInt(frequency);
		String limit = ((String[]) ActionContext.getContext().getParameters().get("checklimit"))[0];
		int limitNum = Integer.parseInt(limit);
		Check newCheck = new Check();
		newCheck.setUser(user);
		newCheck.setName(checkName);
		newCheck.setUrl(checkUrl);
		newCheck.setFrequency(frequencyNum);
		newCheck.setTimesLimit(limitNum);
		CheckDAO.newInstance().saveCheck(newCheck);
		CheckJobManager.newInstance().scheduleMonitorJob(newCheck);
		user.getUrlChecks().add(newCheck);
		ActionContext.getContext().getSession().put("LOGINUSER", user);
		return ActionSupport.SUCCESS;
	}

}
