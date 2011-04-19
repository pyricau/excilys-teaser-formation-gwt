package com.excilys.formation.gwt.teaser.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GwtTeaser implements EntryPoint {

	private final RegisterServiceAsync service = GWT.create(RegisterService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

	}
}
