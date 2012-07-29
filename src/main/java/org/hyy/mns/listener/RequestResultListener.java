package org.hyy.mns.listener;

import org.hyy.mns.models.RequestResult;

public interface RequestResultListener {

	public void whenGotResult(RequestResult thisResult, RequestResult previousResult);
	
}
