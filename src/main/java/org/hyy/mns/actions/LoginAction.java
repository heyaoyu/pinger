package org.hyy.mns.actions;

import org.hyy.mns.daos.UserDAO;
import org.hyy.mns.models.*;

import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionSupport;

public class LoginAction extends ActionSupport{

	private String errorMsg = "";
	
	public String execute(){
		String username = ((String[]) ActionContext.getContext().getParameters().get("username"))[0];
		String password = ((String[]) ActionContext.getContext().getParameters().get("password"))[0];
		User loginUser = UserDAO.newInstance().getUser(username, password);
		if(loginUser==null){
			errorMsg = "Login Error";
			return ActionSupport.ERROR;
		}
		ActionContext.getContext().getSession().put("LOGINUSER", loginUser);
		return ActionSupport.SUCCESS;
	}
	
	public String getErrorMsg(){
		return errorMsg;
	}
	
}
