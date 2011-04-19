package com.excilys.formation.gwt.teaser.shared;

public enum GwtTrainingSession {
	
	ON_28_APRIL("28 avril 2011"), ON_4_MAY("4 mai 2011"), ON_5_MAY("5 mai 2011");
	
	private final String prettyName;

	private GwtTrainingSession(String prettyName) {
		this.prettyName = prettyName;
	}

	public String getPrettyName() {
		return prettyName;
	}

}
