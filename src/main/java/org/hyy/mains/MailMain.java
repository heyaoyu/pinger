package org.hyy.mains;

import org.hyy.mns.core.EmailUtil;

public class MailMain {

	public static void main(String[] args) {
		EmailUtil.newInstance().sendNotification("heyaoyu@gmail.com", "Test, Pls ignore.", "Test");
		System.out.println("Done");
	}
	
}