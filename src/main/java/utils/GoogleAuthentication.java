package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class GoogleAuthentication extends Authenticator {

	PasswordAuthentication PasswordAuthentication;
	
	public GoogleAuthentication() {
		try {
			Properties prop = new Properties();
			prop.load(new FileReader(
					getClass().getResource("../prop/google_mail.properties").getPath()
				));
			String id = prop.getProperty("id");
			String pw = prop.getProperty("pw");
			PasswordAuthentication = new PasswordAuthentication(id,pw);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public PasswordAuthentication getPasswordAuthentication() {
		return PasswordAuthentication;
	}

	public Properties getProp() {
		Properties prop = new Properties();
		prop.put("mail.smtp.host","smtp.gmail.com");
		prop.put("mail.smtp.port","587");
		prop.put("mail.smtp.starttls.enable","true");
		prop.put("mail.smtp.auth", "true");
		// TLS - 587 , SSL - 465
		return prop;
	}
}