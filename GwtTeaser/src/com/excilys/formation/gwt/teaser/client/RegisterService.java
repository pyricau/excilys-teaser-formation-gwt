package com.excilys.formation.gwt.teaser.client;

import com.excilys.formation.gwt.teaser.shared.GwtTrainingSession;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("register")
public interface RegisterService extends RemoteService {
	boolean register(GwtTrainingSession trainingSession, String message);
}
