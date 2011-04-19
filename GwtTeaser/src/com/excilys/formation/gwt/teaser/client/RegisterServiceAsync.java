package com.excilys.formation.gwt.teaser.client;

import com.excilys.formation.gwt.teaser.shared.GwtTrainingSession;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RegisterServiceAsync {
	void register(GwtTrainingSession trainingSession, String message, AsyncCallback<Boolean> callback);
}
