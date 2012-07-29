package org.hyy.mns.daos;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hyy.mns.core.AppProperties;

public class HibernateUtils {

	private SessionFactory sf = null;
	
	private HibernateUtils(){
		String dbhost = AppProperties.newInstance().getString("pinger.hibernate.db.host");
		String dbport = AppProperties.newInstance().getString("pinger.hibernate.db.port");
		String dbname = AppProperties.newInstance().getString("pinger.hibernate.db.name");
		String connUrl = "jdbc:mysql://"+dbhost+":"+dbport+"/"+dbname+"?zeroDateTimeBehavior=convertToNull";
		String dbuser = AppProperties.newInstance().getString("pinger.hibernate.db.username");
		String dbpswd = AppProperties.newInstance().getString("pinger.hibernate.db.password");
		Properties props = new Properties();
		props.setProperty("hibernate.connection.url", connUrl);
		props.setProperty("hibernate.connection.username", dbuser);
		props.setProperty("hibernate.connection.password", dbpswd);
		sf = new Configuration().configure().addProperties(props).buildSessionFactory();
	}
	
	private static HibernateUtils instance;
	
	public static HibernateUtils newInstance(){
		if(instance==null){
			instance = new HibernateUtils();
		}
		return instance;
	}
	
	public SessionFactory getSessionFactory(){
		return sf;
	}
	
}
