package pl.com.webmail;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.com.ContextConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ContextConfiguration.class)
public class MailSenderTest extends TestCase {

	@Autowired
	MailSender mailSender;

	// Polecam: clean install -Dmaven.test.skip=true
	private final String subject = "MAIL TESTOWY";
	private final String address = "newsletter.pollub@gmail.com";
	private final String emailText = "EMAIL TESTOWY";
	
	@Test
	public void testSendEmail()
	{
		mailSender.setSslConnection(false);
		mailSender.sendEmail(emailText, subject, address);
	}
	
	@Test
	public void testSendEmailSSL()
	{
		mailSender.setSslConnection(true);
		mailSender.sendEmail(emailText, subject, address);
	}
	
	
}
