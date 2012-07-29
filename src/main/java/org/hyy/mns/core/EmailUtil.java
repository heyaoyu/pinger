package org.hyy.mns.core;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {

	private static EmailUtil instance;
	
	private Properties props;
	private Authenticator auth;
	private String fromAddress;
	
	private EmailUtil(){
		props = new Properties();
		props.put("mail.smtp.host", AppProperties.newInstance().getString("pinger.mail.smtp.host"));
		props.put("mail.smtp.auth", AppProperties.newInstance().getString("pinger.mail.smtp.auth"));
		auth = new Authenticator(){
	    	public PasswordAuthentication getPasswordAuthentication(){
	    		String username=AppProperties.newInstance().getString("pinger.mail.smtp.username");
	    		String pwd = AppProperties.newInstance().getString("pinger.mail.smtp.password");
	    		return new PasswordAuthentication(username,pwd);
	    	}
	    };
	    fromAddress = AppProperties.newInstance().getString("pinger.mail.smtp.from");
	}
	
	public static EmailUtil newInstance(){
		if(instance==null){
			instance = new EmailUtil();
		}
		return instance;
	}
	
	public void sendNotification(String toAddress, String subject, String content){
		Session s = Session.getDefaultInstance(props, auth);
	    s.setDebug(false);
		MimeMessage message = new MimeMessage(s);
		InternetAddress from;
		try {
			from = new InternetAddress(fromAddress);
			message.setFrom(from);
			InternetAddress to = new InternetAddress(toAddress);
			message.setRecipient(Message.RecipientType.TO, to);
			message.setSubject(subject);
			message.setText(content);
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
}
