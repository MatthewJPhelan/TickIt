package Email;

import java.io.IOException;
import java.util.ArrayList;

import javax.mail.MessagingException;

public class SendEmailTest {
	
	public static void main (String args[]) {
		try {
			Email.sendEmail("mjp492@cs.bham.ac.uk", "suhhh doood", "pretty sweet I guess");
		} catch (IOException e) {
			System.out.println("a properties file was not found by email class");
			e.printStackTrace();
		} catch (MessagingException e) {
			System.out.println("messaging exception thrown");
			e.printStackTrace();
		}
	}

}

