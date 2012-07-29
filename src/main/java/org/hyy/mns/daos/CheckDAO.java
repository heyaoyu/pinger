package org.hyy.mns.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hyy.mns.models.Check;
import org.hyy.mns.models.User;

public class CheckDAO {

	public static CheckDAO newInstance() {
		if (instance == null) {
			instance = new CheckDAO();
		}
		return instance;
	}

	private static CheckDAO instance = null;

	private SessionFactory sessionFac = null;

	private CheckDAO() {
		sessionFac = HibernateUtils.newInstance().getSessionFactory();
	}

	public void saveCheck(Check check) {
		Session session = sessionFac.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(check);
		tx.commit();
		if(session.isOpen())
			session.close();
	}

	public List<Check> getAllNeedMonitorChecks() {
		Session session = sessionFac.getCurrentSession();
		Transaction tx = session.beginTransaction();
		String hql = "from Check where needMonitor=true";
		Query query = session.createQuery(hql);
		List<Check> list = (List<Check>) query.list();
		tx.commit();
		if (session.isOpen())
			session.close();
		return list;
	}

	public Check getCheck(long cid) {
		Session session = sessionFac.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Check check = (Check) session.get(Check.class, cid);
		tx.commit();
		if (session.isOpen())
			session.close();
		return check;
	}

	public Check updateCheck(Check check, int newFrequencyNum, int newLimit,
			String name, String url, boolean monitor) {
		Session session = sessionFac.getCurrentSession();
		Transaction tx = session.beginTransaction();
		check.setFrequency(newFrequencyNum);
		check.setTimesLimit(newLimit);
		check.setName(name);
		check.setUrl(url);
		check.setNeedMonitor(monitor);
		session.update(check);
		tx.commit();
		if (session.isOpen())
			session.close();
		return this.getCheck(check.getCid());
	}

}
