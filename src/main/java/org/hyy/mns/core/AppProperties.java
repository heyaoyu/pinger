package org.hyy.mns.core;

import java.io.IOException;
import java.util.Properties;

public class AppProperties {

	private Properties props = null;
	
	private static AppProperties instance = null;
	
	public static AppProperties newInstance(){
		if(instance==null){
			instance = new AppProperties();
		}
		return instance;
	}
	
	private AppProperties(){
		props = new Properties();
		try {
			props.load(getClass().getResourceAsStream("/pinger.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getString(String entryName){
		return props.getProperty(entryName);
	}
	
}
