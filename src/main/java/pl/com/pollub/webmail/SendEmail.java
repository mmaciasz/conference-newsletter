package pl.com.pollub.webmail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;

public class SendEmail 
{
	//@Value("webmail.login")
	private String login = "newsletter.pollub";
	
	//@Value("webmail.password")
	private static String password = "Pollub12345!";
	
	//@Value("webmail.senderEmail")
	private static String senderEmail = "newsletter.pollub@gmail.com";
	
	private static boolean sslConnection = false;
	
	private static Session session;
	private static Session sessionSSL;
	
	public void setSession()
	{
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(login, password);
			}
		});
	}
	
	public void setSSLSession()
	{
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		sessionSSL = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(login, password);
			}
		});
	}
	
	public void sendEmail(String emailText, String subject, String address)
	{
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

			System.out.println("Wysłano email! Na adres: " + address);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	

	public boolean isSslConnection() {
		return sslConnection;
	}

	public void setSslConnection(boolean sslConnection) {
		this.sslConnection = sslConnection;
	}

}
