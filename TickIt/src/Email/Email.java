package Email;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	

	public static void sendEmail (String toAddress, String subject, String message) throws IOException, MessagingException {
				
			// Get system properties
		      Properties properties = new Properties();
		      properties.setProperty("mail.smtp.port", "587");
		      properties.setProperty("mail.smtp.auth", "true");
		      properties.setProperty("mail.smtp.starttls.enable", "true");

		      // Get the default Session object.
		      Session session = Session.getDefaultInstance(properties);

		      try {
		         // Create a default MimeMessage object.
		         MimeMessage email = new MimeMessage(session);

		         // Set Subject: header field
		         email.setSubject(subject);

		         // Now set the actual message
		         email.setContent(message, "text/html");
		         
		         email.addRecipients(Message.RecipientType.TO, toAddress);
		         
		         Transport transport = session.getTransport("smtp");
		         transport.connect("smtp.gmail.com", "Tickitteam@gmail.com", "123password321");
		         transport.sendMessage(email, email.getAllRecipients());
		         System.out.println("Sent message successfully....");
		      } catch (MessagingException mex) {
		         mex.printStackTrace();
		      }
	}
}
