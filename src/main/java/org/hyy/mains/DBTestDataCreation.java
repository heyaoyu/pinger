package org.hyy.mains;

import org.hyy.mns.daos.CheckDAO;
import org.hyy.mns.daos.UserDAO;
import org.hyy.mns.models.Check;
import org.hyy.mns.models.User;

public class DBTestDataCreation {
	
	public static void main(String[] args) throws InterruptedException {
		User user = new User();
		user.setUsername("guest");
		user.setPassword("guest");
		user.setEmail("heyaoyu@gmail.com");
		UserDAO.newInstance().saveUser(user);
		Check check = new Check();
		check.setFrequency(1);
		check.setName("baidu");
		check.setNotifyWhenUp(true);
		check.setTimesLimit(3);
		check.setUrl("http://baidu.com/");
		check.setUser(user);
		CheckDAO.newInstance().saveCheck(check);
//		User user2 = new User();
//		user2.setUsername("nick.chen");
//		user2.setPassword("pswd");
//		user2.setEmail("nick.chen@gmail.com");
//		UserDAO.newInstance().saveUser(user2);
//		Check check2 = new Check();
//		check2.setFrequency(1);
//		check2.setName("test");
//		check2.setNotifyWhenUp(true);
//		check2.setTimesLimit(3);
//		check2.setUrl("https://clarity.tfn.com/");
//		check2.setNeedMonitor(false);
//		CheckDAO.newInstance().saveCheck(check2);
//		CheckDAO.newInstance().associateUser(user2, check2);
//		Check check = CheckDAO.newInstance().getAllChecks().get(1);
//		RequestResult thisResult = new RequestResult();
//		thisResult.setCheck(check);
//		thisResult.setStatus(200);
//		thisResult.setStatusCode("ok");
//		thisResult.setTs(new Date());
//		thisResult.setSuccess(true);
//		RRDAO.newInstance().saveResult(thisResult);
	}
	
}