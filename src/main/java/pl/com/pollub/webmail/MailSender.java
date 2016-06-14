package pl.com.pollub.webmail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class MailSender {

    private static final Logger log = LoggerFactory.getLogger(MailSender.class);

    @Value("${webmail.login}")
    private String login;

    @Value("${webmail.password}")
    private String password;

    @Value("${webmail.senderEmail}")
    private String senderEmail;

    private static boolean sslConnection = false;

    private static Session session;
    private static Session sessionSSL;

    public void setSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(login, password);
            }
        });
    }

    public void setSSLSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        sessionSSL = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(login, password);
            }
        });
    }

    public synchronized void sendEmail(String emailText, String subject, String address) {
        try {
            Message message;
            if (sslConnection) {
                setSSLSession();
                message = new MimeMessage(sessionSSL);
            } else {
                setSession();
                message = new MimeMessage(session);
            }

            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address));
            message.setSubject(subject);
            message.setText(emailText);

            Transport.send(message);

            log.info("Wysłano email! Na adres: " + address);

        } catch (MessagingException e) {
            log.error("Error sending email.", e);
        }
    }


    public boolean isSslConnection() {
        return sslConnection;
    }

    public void setSslConnection(boolean sslConnection) {
        MailSender.sslConnection = sslConnection;
    }

}
