package org.hyy.mns.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hyy.mns.listener.EventEmitter;
import org.hyy.mns.listener.LimitCheckListener;
import org.hyy.mns.listener.NotifyUpListener;
import org.hyy.mns.models.Check;
import org.hyy.mns.models.RequestResult;

public class RRDAO extends EventEmitter{
	
	private SessionFactory sessionFac = null;
	
	private static RRDAO dao = null;
	
	public static RRDAO newInstance(){
		if(dao==null){
			dao = new RRDAO();
		}
		return dao;
	}
	
	private RRDAO(){
		sessionFac = HibernateUtils.newInstance().getSessionFactory();
		((EventEmitter)this).addListener(new LimitCheckListener());
		((EventEmitter)this).addListener(new NotifyUpListener());
	}
	
	public synchronized void saveResult(RequestResult thisResult){
		Session session = sessionFac.getCurrentSession();
		Transaction tx = session.beginTransaction();
		RequestResult previousResult = getPreviousResult(thisResult, thisResult.getCheck(), session);
		if(thisResult!=null){
			if(!thisResult.isSuccess()){
				if(previousResult!=null){
					thisResult.setFailedTimes(previousResult.getFailedTimes()+1);
				}else{
					thisResult.setFailedTimes(1);
				}
			}
			session.save(thisResult);
		}
		tx.commit();
		if(session.isOpen())
			session.close();
		((EventEmitter)this).fireEvent(thisResult, previousResult);
	}
	
	private RequestResult getPreviousResult(RequestResult thisResult, Check check, Session session){
		String hql = "from RequestResult where check=:check and ts<:timestamp order by ts desc";
		Query query = session.createQuery(hql);
		query.setEntity("check", check);
		query.setTimestamp("timestamp", thisResult.getTs());
		List list = query.list();
		if(list!=null&&list.size()>0){
			RequestResult res = (RequestResult) query.list().get(0);
			return res;
		}else{
			return null;
		}
	}
	
	public List<RequestResult> getResultBy(Check check){
		Session session = sessionFac.getCurrentSession();
		Transaction tx = session.beginTransaction();
		String hql = "from RequestResult where check=:check order by ts desc";
		Query query = session.createQuery(hql);
		query.setMaxResults(5);
		query.setEntity("check", check);
		List<RequestResult> list = query.list();
		tx.commit();
		if(session.isOpen())
			session.close();
		return list;
	}

}
