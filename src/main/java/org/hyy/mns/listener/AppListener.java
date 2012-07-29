package org.hyy.mns.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.hyy.mns.core.CheckJobManager;
import org.hyy.mns.daos.CheckDAO;
import org.hyy.mns.models.Check;

public class AppListener implements ServletContextListener{

	private static final Logger LOG = Logger.getLogger(AppListener.class);
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		LOG.warn("App is starting, activating all Checks...");
		for(Check check : CheckDAO.newInstance().getAllNeedMonitorChecks())
			CheckJobManager.newInstance().scheduleMonitorJob(check);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		LOG.warn("App is closing, deactivating Scheduler...");
		CheckJobManager.newInstance().stopScheduler();
	}

}
