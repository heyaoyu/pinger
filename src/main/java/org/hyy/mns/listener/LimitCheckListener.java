package org.hyy.mns.listener;

import org.apache.log4j.Logger;
import org.hyy.mns.core.EmailUtil;
import org.hyy.mns.models.Check;
import org.hyy.mns.models.RequestResult;
import org.hyy.mns.models.User;

public class LimitCheckListener implements RequestResultListener{

	private static final Logger LOG = Logger.getLogger(LimitCheckListener.class);
	
	@Override
	public void whenGotResult(RequestResult thisResult, RequestResult previousResult) {
		if(thisResult.getFailedTimes()==thisResult.getCheck().getTimesLimit()){
			Check check = thisResult.getCheck();
			User user = check.getUser();
			LOG.warn("Sending email to [For Continous Fail] "+user.getUsername()+" and the email is "+user.getEmail());
			String subject = "Your check URL: ["+check.getUrl()+"] is down @ "+ thisResult.getTs() +".";
			EmailUtil.newInstance().sendNotification(user.getEmail(), subject, "As subject.");
		}
	}

}
