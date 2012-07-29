package org.hyy.mns.listener;

import java.util.*;

import org.hyy.mns.models.RequestResult;

public class EventEmitter {

	private List<RequestResultListener> listeners = new ArrayList<RequestResultListener>();
	
	public void addListener(RequestResultListener listener){
		if(listeners.indexOf(listener)==-1){
			listeners.add(listener);
		}
	}
	
	public void removeListener(RequestResultListener listener){
		int index = listeners.indexOf(listener);
		if(index!=-1){
			listeners.remove(index);
		}
	}
	
	public void fireEvent(RequestResult thisResult, RequestResult previousResult){
		for(RequestResultListener rrl : listeners){
			rrl.whenGotResult(thisResult, previousResult);
		}
	}
	
}
