package pl.com.webmail;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.com.pollub.ContextConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ContextConfiguration.class)
public class SendEmailTest extends TestCase {
	
	
	//PRZY TESTOWANIU ZMIENIC ADRES DO WYSYLANIA ZEBY MI SKRZYNKI NIE ZASMIECAC!! :P
	public String subject = "MAIL TESTOWY";
	public String address = "wojtekkowalczyk90@gmail.com";
	public String emailText = "EMAIL TESTOWY";
	
	@Test
	public void testSendEmail()
	{
		SendEmail sendEmail = new SendEmail();
		sendEmail.setSslConnection(false);
		sendEmail.sendEmail(emailText, subject, address);
	}
	
	@Test
	public void testSendEmailSSL()
	{
		SendEmail sendEmail = new SendEmail();
		sendEmail.setSslConnection(true);
		sendEmail.sendEmail(emailText, subject, address);
	}
	
	
}
