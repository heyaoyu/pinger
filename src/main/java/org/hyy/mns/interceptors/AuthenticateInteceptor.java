package org.hyy.mns.interceptors;

import org.hyy.mns.models.User;

import com.opensymphony.xwork.Action;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.Interceptor;

public class AuthenticateInteceptor implements Interceptor{

	public void destroy() {
		
	}

	public void init() {
		
	}

	public String intercept(ActionInvocation ai) throws Exception {
		User user = (User) ai.getInvocationContext().getSession().get("LOGINUSER");
		if(user==null){
			return Action.LOGIN;
        }
		return ai.invoke();
	}

}
