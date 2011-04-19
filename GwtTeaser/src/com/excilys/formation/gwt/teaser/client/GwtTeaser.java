package com.excilys.formation.gwt.teaser.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class GwtTeaser implements EntryPoint {

    private final RegisterServiceAsync service = GWT.create(RegisterService.class);

    public void onModuleLoad() {
        RootLayoutPanel.get().add(new MegaCoolView(service));
    }
}
