package org.hyy.mns.listener;

import org.apache.log4j.Logger;
import org.hyy.mns.core.EmailUtil;
import org.hyy.mns.models.Check;
import org.hyy.mns.models.RequestResult;
import org.hyy.mns.models.User;

public class NotifyUpListener implements RequestResultListener{
	
	private static final Logger LOG = Logger.getLogger(NotifyUpListener.class);
	
	@Override
	public void whenGotResult(RequestResult thisResult, RequestResult previousResult) {
		// 1st result: previousResult is null.
		if(previousResult!=null&&thisResult.getCheck().isNotifyWhenUp()){
			Check check = thisResult.getCheck();
			User user = check.getUser();
			if(thisResult.isSuccess()&&!previousResult.isSuccess()&&previousResult.getFailedTimes()>=check.getTimesLimit()){
				LOG.warn("Sending email to [For Notify Up] "+user.getUsername()+" and the email is "+user.getEmail());
				String subject = "Your check URL: ["+check.getUrl()+"] is up again @ "+ thisResult.getTs() +".";
				EmailUtil.newInstance().sendNotification(user.getEmail(), subject, "As subject.");
			}
		}
	}
	
}
