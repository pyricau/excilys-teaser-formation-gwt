package com.excilys.formation.gwt.teaser.client;

import com.excilys.formation.gwt.teaser.shared.GwtTrainingSession;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.Widget;

public class MegaCoolView extends Composite {

    interface MyUiBinder extends UiBinder<Widget, MegaCoolView> {
    }

    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    private final RegisterServiceAsync service;

    public MegaCoolView(RegisterServiceAsync service) {
        this.service = service;
        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiHandler("register1")
    void register1(ClickEvent e) {
        registrationDialog(GwtTrainingSession.ON_28_APRIL);
    }

    @UiHandler("register2")
    void register2(ClickEvent e) {
        registrationDialog(GwtTrainingSession.ON_4_MAY);
    }

    @UiHandler("register3")
    void register3(ClickEvent e) {
        registrationDialog(GwtTrainingSession.ON_5_MAY);
    }

    private void registrationDialog(final GwtTrainingSession trainingSession) {
        final DialogBox dialogBox = new DialogBox(true, true);
        dialogBox.setGlassEnabled(true);
        dialogBox.setAnimationEnabled(true);

        dialogBox.setText("Inscription à la formation GWT du " + trainingSession.getPrettyName());

        Label label = new Label("Un petit message ?");

        final RichTextArea richTextArea = new RichTextArea();
        
        final RichTextToolbar toolBar = new RichTextToolbar(richTextArea);
        richTextArea.setWidth("100%");

        Button registerButton = new Button("Je viens !");
        registerButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                dialogBox.hide();
                String message = richTextArea.getHTML();
                doRegister(trainingSession, message);
            }
        });
        
        FlowPanel flowPanel = new FlowPanel();

        flowPanel.add(label);
        flowPanel.add(toolBar);
        flowPanel.add(richTextArea);
        flowPanel.add(registerButton);
        
        dialogBox.add(flowPanel);

        dialogBox.center();
    }

    private void doRegister(GwtTrainingSession trainingSession, String message) {
        service.register(trainingSession, message, new AsyncCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                if (result == null || result == false) {
                    showErrorDialog();
                } else {
                    showRegisteredDialog();
                }
            }

            @Override
            public void onFailure(Throwable caught) {
                showErrorDialog();
            }
        });
    }

    private void showErrorDialog() {
        final DialogBox dialogBox = new DialogBox(true, true);
        dialogBox.setGlassEnabled(true);
        dialogBox.setAnimationEnabled(true);

        dialogBox.setText("Erreur inconnue");

        Label label = new Label("WTF ?? Visiblement, un ptit problème... contactez pyricau@excilys.com et lâchez-vous, ça bug!");

        Button button = new Button("Fermer cette boîte d'erreur débile");
        button.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                dialogBox.hide();
            }
        });
        
        FlowPanel flowPanel = new FlowPanel();
        flowPanel.add(label);
        flowPanel.add(button);

        dialogBox.add(flowPanel);
        dialogBox.center();
    }
    
    private void showRegisteredDialog() {
        final DialogBox dialogBox = new DialogBox(true, true);
        dialogBox.setGlassEnabled(true);
        dialogBox.setAnimationEnabled(true);

        dialogBox.setText("Inscription enregistrée!");

        Label label = new Label("Un mail de confirmation de la demande d'inscription a été envoyé.");

        Button button = new Button("J'ai fini, je vais voir mes mails");
        button.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                dialogBox.hide();
                Window.open("https://mail.google.com/a/excilys.com/", "_self", ""); 
            }
        });
        
        FlowPanel flowPanel = new FlowPanel();
        flowPanel.add(label);
        flowPanel.add(button);

        dialogBox.add(flowPanel);
        dialogBox.center();
    }

}
