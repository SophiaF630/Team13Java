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

		String[] to = {"feng.yuxi@u.nus.edu", "e0338082@u.nus.edu"};
		String subject = "Enrollment Confirmation";
		String body = "Hi Applicant Name! Congratulations! Your registration is confirmed! You have successfully completed registration. Below is a list of the details of the course you have registered : If you have further queries or need clarification, you may reach us at our official email at SA47Team13@gmail.com or call us at 62314231. Thank you";
		javaEmail.setMailServerProperties();
		javaEmail.createEmailMessage(to, subject, body);
		javaEmail.sendEmail();
	}

}
