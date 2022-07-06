package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.MemberVO;
import repositories.MemberDAO;
import repositories.MemberDAOImpl;
import utils.GoogleAuthentication;




public class MemberServiceImpl implements MemberService {

	MemberDAO dao = new MemberDAOImpl();
	
	@Override
	public void memberJoin(HttpServletRequest request, HttpServletResponse response) {
		// id pass rePass name age gender
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String rePass = request.getParameter("rePass");
		String name = request.getParameter("name");
		String ageStr = request.getParameter("age");
		String gender = request.getParameter("gender");
		int age = Integer.parseInt(ageStr);
		
		response.setContentType("text/html;charset=utf-8");
		
		try {
			PrintWriter pw = response.getWriter();
			pw.print("<script>");
			if(!pass.equals(rePass)) {
				pw.print("alert('비밀번호가 일치하지 않습니다.');");
				pw.print("history.go(-1);");
				pw.print("</script>");
				return;
			}
			// 회원 아이디로 일치하는 사용자 정보가 존재하는지 확인
			MemberVO member = dao.getMemberById(id);
			if(member != null) {
				pw.print("alert('이미 존재하는 아이디입니다.');");
				pw.print("history.go(-1);");
				pw.print("</script>");
				return;
			}
			member = new MemberVO(id,pass,name,age,gender);
			boolean isJoin = dao.memberJoin(member);
			if(isJoin) {
				// 회원가입 성공
				pw.print("alert('회원가입 성공');");
				pw.print("location.href='login.mc';");
			}else {
				// 회원가입 실패
				pw.print("alert('회원가입 실패');");
				pw.print("history.back();");
			}
			pw.print("</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean memberLogin(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		boolean isLogin = false;
		MemberVO member = dao.memberLogin(id, pass);
		if(member != null) {
			isLogin = true;
			HttpSession session = request.getSession();
			session.setAttribute("member", member);
			String check = request.getParameter("check");
			if(check != null) {
				Cookie cookie = new Cookie("id",member.getId());
				cookie.setMaxAge(60*60*24*15);
				cookie.setPath("/");
				response.addCookie(cookie);
				
			}
		}
		
		return isLogin;
	}

	@Override
	public void memberUpdate(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		String ageStr = request.getParameter("age");
		String gender = request.getParameter("gender");
		String numStr = request.getParameter("num");
		int age = Integer.parseInt(ageStr);
		int num = Integer.parseInt(numStr);
		MemberVO member = new MemberVO(id,pass,name,age,gender);
		member.setNum(num);
		boolean isUpdate = dao.memberUpdate(member);
		String url = "info.mc";
		String msg = "회원정보 수정 완료";
		if(isUpdate) {
			// 수정된 회원정보 세션 갱신
			MemberVO vo = dao.getMemberById(id);
			request.getSession().setAttribute("member", vo);
		}else {
			url ="update.mc";
			msg = "회원정보 수정 실패";
		}
		
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.print("<script>");
			pw.print("alert('"+msg+"');");
			pw.print("location.href='"+url+"';");
			pw.print("</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

	}

	@Override
	public void logOut(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute("member");
		Cookie cookie = new Cookie("id","");
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);

	}

	@Override
	public void withDraw(HttpServletRequest request, HttpServletResponse response) {
		String tempPass = request.getParameter("tempPass");
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO)session.getAttribute("member");
		
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.print("<script>");
			if(member !=null && member.getPass().equals(tempPass)) {
				// 탈퇴처리
				dao.withDrawMember(member.getNum());
				logOut(request,response);
				pw.print("location.href='test';");
			}else {
				// 회원탈퇴 실패
				pw.print("alert('회원탈퇴 실패');");
				pw.print("history.go(-1);");
			}
			pw.print("</script>");
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

	@Override
	public void findPassSubmit(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		
		boolean isCheck = dao.checkMember(id, name);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			if(!isCheck) {
				System.out.println("일치하는 정보 없음");
				throw new NullPointerException("일치하는 사용자 정보가 없음.");
			}
			
			// code와 함께 사용자가 작성한 이메일로 메세지 전송
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<5; i++) {
				int random = (int)(Math.random()*10);
				sb.append(random);
			}
			String code = sb.toString();
			System.out.println(code);
			// 메일 발송 전에 DB에 저장
			dao.addPassCode(id, code);
			
			// code 가 작성된 메일 전송
			GoogleAuthentication ga 
				= new GoogleAuthentication();
			Session session 
				= Session.getDefaultInstance(ga.getProp(), ga);
			MimeMessage msg = new MimeMessage(session);
			InternetAddress toAddress
			= new InternetAddress(id);
			InternetAddress fromAddress 
			= new InternetAddress("master@koreate.net","관리자");
			// mail 전송 시간 정보
			msg.setSentDate(new Date());
			msg.setHeader("Content-Type", "text/html;charset=utf-8");
			msg.setRecipient(Message.RecipientType.TO, toAddress);
			msg.setFrom(fromAddress);
			msg.setSubject("비밀번호 찾기!!","UTF-8");
			StringBuilder mail = new StringBuilder();
			mail.append("<!DOCTYPE html>");
			mail.append("<html>");
			mail.append("<head>");
			mail.append("<meta charset=\"UTF-8\" />");
			mail.append("</head>");
			mail.append("<body>");
			mail.append("<h1>@@@ 사이트 비밀번호 찾기 이메일 인증!</h1>");
			// 'http://192.168.1.24:8080/contextPath/passAccept.mc' method='POST'
			mail.append("<form action='http://192.168.1.24:8080");
			mail.append(request.getContextPath()); 
			mail.append("/passAccept.mc' method='POST' onsubmit='window.open(\"\",\"w\")'target=>'w'>");
			mail.append("<input type='hidden' name='id' value='"+id+"'/>");
			mail.append("<input type='hidden' name='code' value='"+code+"'/>");
			mail.append("<button>이메일 인증 완료</button>");
			mail.append("</form>");
			mail.append("</body>");
			mail.append("</html>");
			String content = mail.toString();
			msg.setContent(content,"text/html;charset=utf-8");
			
			Transport.send(msg);
			
			pw.print("<script>");
			pw.print("alert('메일이 정상적으로 발송되었습니다. 확인해주세요.');");
			pw.print("location.href='test';");
			pw.print("</script>");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			pw.print("<script>");
			pw.print("alert('서비스에 문제가 있습니다. 다시 이용해 주세요. "+e.getMessage()+"');");
			pw.print("location.href='login.mc';");
			pw.print("</script>");
		}
		
	}

	@Override
	public void changePassCode(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String code = request.getParameter("code");
		System.out.println(id+":"+code);
		
		try {
			boolean isCheck = dao.checkPassCode(id, code);
			if(isCheck) {
				request.setAttribute("id", id);
				request.setAttribute("code", code);
				request.getRequestDispatcher("/member/changePass.jsp")
				.forward(request, response);
			}else {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter pw = response.getWriter();
				pw.print("<script>");
				pw.print("alert('잘못된 요청 입니다.');");
				pw.print("location.href='login.mc';");
				pw.print("</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void changePass(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String code = request.getParameter("code");
		String pass = request.getParameter("pass");
		
		boolean isCheck = dao.checkPassCode(id, code);
		
		response.setContentType("text/html;charset=utf-8");
		
		try {
			PrintWriter pw = response.getWriter();
			pw.print("<script>");
			if(isCheck) {
				dao.changePass(id, pass);
				pw.print("alert('변경 요청 처리 완료');");
			}else {
				pw.print("alert('올바른 접근이 아닙니다.');");
			}
			pw.print("location.href='login.mc';");
			pw.print("</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

