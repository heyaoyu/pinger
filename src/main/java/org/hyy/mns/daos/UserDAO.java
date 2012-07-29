package org.hyy.mns.daos;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hyy.mns.models.Check;
import org.hyy.mns.models.User;

public class UserDAO {

	public static UserDAO newInstance(){
		if(instance==null){
			instance = new UserDAO();
		}
		return instance;
	}
	
	private static UserDAO instance = null;
	
	private SessionFactory sessionFac = null;
	
	private UserDAO(){
		sessionFac = HibernateUtils.newInstance().getSessionFactory();
	}
	
	public void saveUser(User user){
		Session session = sessionFac.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.save(user);
		tx.commit();
		if(session.isOpen())
			session.close();
	}
	
	public User getUser(String username, String password){
		Session session = sessionFac.getCurrentSession();
		Transaction tx = session.beginTransaction();
		String hql = "from User where username=:username and password=:password";
		Query query = session.createQuery(hql);
		query.setString("username", username);
		query.setString("password", password);
		List userlist = query.list();
		User user = null;
		if(userlist!=null&&userlist.size()>0){
			user = (User) userlist.get(0);
		}
		tx.commit();
		if(session.isOpen())
			session.close();
		return user;
	}
	
	public Set<Check> getChecks(User user){
		Session session = sessionFac.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Set<Check> checks = user.getUrlChecks();
		tx.commit();
		if(session.isOpen())
			session.close();
		return checks;
	}
	
}
