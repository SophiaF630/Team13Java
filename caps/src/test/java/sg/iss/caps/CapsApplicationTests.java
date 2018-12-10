package sg.iss.caps;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sg.iss.caps.services.EmailServiceImpl;

public class CapsApplicationTests {


	@Test
	public void testSendEmail() throws AddressException, MessagingException {

		EmailServiceImpl javaEmail = new EmailServiceImpl();

		javaEmail.setMailServerProperties();
		javaEmail.createEmailMessage();
		javaEmail.sendEmail();
	}

}
