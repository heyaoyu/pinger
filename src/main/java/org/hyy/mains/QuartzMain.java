package org.hyy.mains;

import org.hyy.mns.core.CheckJobManager;
import org.hyy.mns.daos.CheckDAO;
import org.hyy.mns.models.Check;

public class QuartzMain {

	public static void main(String[] args) {
		for(Check check : CheckDAO.newInstance().getAllNeedMonitorChecks()){
			handleCheck(check);
		}
		Check check = CheckDAO.newInstance().getAllNeedMonitorChecks().get(0);
		try {
			Thread.sleep(1*3*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		CheckJobManager.newInstance().removeMonitorJob(check);
		try {
			Thread.sleep(1*2*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(new Runnable(){
			public void run(){
				CheckJobManager.newInstance().stopScheduler();
			}
		}).start();
	}
	
	private static void handleCheck(Check check){
		CheckJobManager.newInstance().scheduleMonitorJob(check);
	}
	
}