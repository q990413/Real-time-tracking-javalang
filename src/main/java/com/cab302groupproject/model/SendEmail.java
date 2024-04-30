package com.cab302groupproject.model;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * A class to send an email.
 */
public class SendEmail {
    private static final String senderEmail = "tranquilify1@gmail.com";
    private static final String senderPassword = "ihghdadzwrakwskb";

    /**
     * Sends an email with the given login code to the given email address.
     * @param receiverEmail The email address of the recipient.
     * @param strCode The login code to be emailed.
     * @return True if the email was successfully sent, or false otherwise.
     */
    public static void sendEmail(String receiverEmail, String strCode) {
        Properties props = System.getProperties(); // Contains host info
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465"); // For gmail
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setText("Your Tranquilify login code is: " + strCode);
            msg.setSubject("Tranquilify Login Code");
            msg.setFrom(new InternetAddress(senderEmail));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(receiverEmail));
            Transport.send(msg);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
