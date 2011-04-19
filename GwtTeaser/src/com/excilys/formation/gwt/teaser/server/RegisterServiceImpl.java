package com.excilys.formation.gwt.teaser.server;

import com.excilys.formation.gwt.teaser.client.RegisterService;
import com.excilys.formation.gwt.teaser.shared.GwtTrainingSession;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class RegisterServiceImpl extends RemoteServiceServlet implements RegisterService {

	public boolean greetServer(GwtTrainingSession session, String message) throws IllegalArgumentException {
		
		return true;
	}

}
