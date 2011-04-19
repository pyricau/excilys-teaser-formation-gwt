package com.excilys.formation.gwt.teaser.server;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.excilys.formation.gwt.teaser.client.RegisterService;
import com.excilys.formation.gwt.teaser.shared.GwtTrainingSession;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class RegisterServiceImpl extends RemoteServiceServlet implements RegisterService {

    public boolean register(GwtTrainingSession trainingSession, String message) {
        try {
            registerThrowing(trainingSession, message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void registerThrowing(GwtTrainingSession trainingSession, String message) throws IllegalArgumentException, UnsupportedEncodingException, MessagingException {
        String username = getThreadLocalRequest().getRemoteUser();
        sendRegistrationMail(trainingSession, username, message);
        sendRegistrationNoticeMail(trainingSession, username);
    }

    private void sendRegistrationMail(GwtTrainingSession trainingSession, String username, String message) throws MessagingException, UnsupportedEncodingException {
        String registerSubject = "Inscription formation GWT de " + username + " pour le " + trainingSession.getPrettyName();
        String registerMsgBody = username + " a demandé à être inscrit à la session de formation GWT du " + trainingSession.getPrettyName() + ".<br />Merci de lui répondre le plus rapidement possible.<br />Son message:<br />" + message;
        // TODO add training@excilys.com slandelle@excilys.com
        sendMail(registerSubject, registerMsgBody, "pyricau@excilys.com", "pmerienne@excilys.com", "mboniface@excilys.com");
    }

    private void sendRegistrationNoticeMail(GwtTrainingSession trainingSession, String username) throws MessagingException, UnsupportedEncodingException {
        String noticeSubject = "Demande d'inscription pour le " + trainingSession.getPrettyName() + " envoyée.";
        String noticeMsgBody = "Votre demande d'inscription <em>(poil au menton)</em> pour la formation GWT du <strong>" + trainingSession.getPrettyName() + "</strong> a été envoyée <em>(poil au nez)</em> à <a href=\"mailto:training@excilys.com\">training@excilys.com</a> <em>(poil au cassis)</em>.";
        sendMail(noticeSubject, noticeMsgBody, username + "@excilys.com");
    }

    private void sendMail(String subject, String msgBody, String... mails) throws MessagingException, UnsupportedEncodingException {
        Session session = Session.getDefaultInstance(new Properties(), null);
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("pyricau@excilys.com", "Application formation GWT"));
        boolean first = true;
        for (String mail : mails) {
            RecipientType recipientType;
            if (first) {
                first = false;
                recipientType = RecipientType.TO;
            } else {
                recipientType = RecipientType.CC;
            }
            msg.addRecipient(recipientType, new InternetAddress(mail, mail));
        }
        msg.setSubject(subject);
        msg.setContent(msgBody, "text/html");
        Transport.send(msg);
    }

}
