package org.hyy.mns.actions;

import org.hyy.mns.daos.CheckDAO;
import org.hyy.mns.models.*;

import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionSupport;

public class ShowCheckAction extends ActionSupport{

	private String cid = "";
	
	public String execute(){
		cid = ((String[]) ActionContext.getContext().getParameters().get("cid"))[0];
		return ActionSupport.SUCCESS;
	}
	
	public Check getCheck(){
		Check check = CheckDAO.newInstance().getCheck(Long.parseLong(cid));
		return check;
	}
	
}
