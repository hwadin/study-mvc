package test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.GoogleAuthentication;

@WebServlet("/googleMailTest")
public class GoogleMailTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	class MyAuthentication extends Authenticator{
		PasswordAuthentication pa;
		
		public MyAuthentication() {
			
			try {
				Properties prop = new Properties();
				String path 
				= getClass().getResource("../prop/google_mail.properties")
				 .getPath();
				System.out.println(path);
				prop.load(new FileReader(path));
				String id = prop.getProperty("id");
				String pw = prop.getProperty("pw");
				System.out.println(id+":"+pw);
				pa = new PasswordAuthentication(id,pw);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public PasswordAuthentication getPasswordAuthentication() {
			return pa;
		}
		
		
	}
	
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// SMTP(Simple Mail Transfer Protocol - 간이 우편 전송 규약)
		Properties prop = new Properties();
		prop.put("mail.smtp.host","smtp.gmail.com");
		prop.put("mail.smtp.auth","true");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.starttls.enable","true");
		
		// MyAuthentication ma = new MyAuthentication();
		GoogleAuthentication ga = new GoogleAuthentication();
		Session session = Session.getDefaultInstance(ga.getProp(), ga);
		
		try {
			MimeMessage msg = new MimeMessage(session);
			// 받는 사람
			InternetAddress to = new InternetAddress(
				"hwajin1478@gmail.com"
			);
			msg.setRecipient(Message.RecipientType.TO, to);
			// TO - 받는사람
			// CC - 참조
			// BCC - 숨은 참조
			InternetAddress from = new InternetAddress(
				"master@koreate.net","MASTER"
			);
			msg.setFrom(from);
			msg.setHeader("Content-Type", "text/html;chrset=utf-8;");
			msg.setSubject("테스트 제목입니다.","utf-8");
			msg.setText("테스트 내용입니다.","UTF-8");
			
			javax.mail.Transport.send(msg);
			System.out.println("메일전송 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("메일전송 실패");
		}
		response.sendRedirect(request.getContextPath()+"/test");
	}

}








