package com.higgsup.bizwebcrawler.methods.common;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.util.Date;
import java.util.Properties;

/**
 * Created by viquynh on 26/07/2017.
 */
public class SendEmail {
    public static void send(String smtpServer, String to, String from, String psw,String subject, String body) throws Exception {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", smtpServer);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        final String login = from;
        final String pwd = psw;
        javax.mail.Authenticator pa = null;
        if (login != null && pwd != null) {
            props.put("mail.smtp.auth", "true");
            pa = new javax.mail.Authenticator() {

                public javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication(login, pwd);
                }
            };
        }
        Session session = Session.getInstance(props, pa);
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(
                to, false));
        msg.setSubject(MimeUtility.encodeText(subject, "utf-8", "B"));
        msg.setContent(body,"text/html; charset=UTF-8" );
        msg.setHeader("X-Mailer", "LOTONtechEmail");
        msg.setSentDate(new Date());
        msg.saveChanges();
        Transport.send(msg);
    }
}
