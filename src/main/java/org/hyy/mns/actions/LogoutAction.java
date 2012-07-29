package org.hyy.mns.actions;

import org.hyy.mns.daos.UserDAO;
import org.hyy.mns.models.*;

import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionSupport;

public class LogoutAction extends ActionSupport{
	public String execute(){
		ActionContext.getContext().getSession().remove("LOGINUSER");
		return ActionSupport.SUCCESS;
	}
}
