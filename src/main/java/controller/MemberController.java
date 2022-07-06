package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;
import service.MemberServiceImpl;

public class MemberController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	MemberService ms = new MemberServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		// Cookie 정보 확인 - 자동 로그인
		MemberService.loginCheck(request);

		System.out.println("MemberController 요청");
		String requestPath = request.getRequestURI();
		System.out.println("요청 전체 경로 : " + requestPath);
		String contextPath = request.getContextPath();
		System.out.println("프로젝트 경로 : " + contextPath);
		String command = requestPath.substring(contextPath.length() + 1);
		System.out.println("command : " + command);
		String view = "";

		if (command.equals("join.mc")) {
			view = "/member/join.jsp";
		}

		if (command.equals("joinSubmit.mc")) {
			// 회원가입 요청 처리
			ms.memberJoin(request, response);
		}

		if (command.equals("login.mc")) {
			view = "/member/login.jsp";
		}

		// 로그인 요청 처리
		if (command.equals("loginSubmit.mc")) {
			boolean isLogin = ms.memberLogin(request, response);
			if (isLogin) {
				// 로그인 성공
				response.sendRedirect(contextPath + "/test");
			} else {
				response.sendRedirect(contextPath + "/login.mc");
			}
		}
		// 회원정보 수정 페이지 요청
		if (command.equals("update.mc")) {
			view = "/member/update.jsp";
		}

		// 회원정보 수정 요청 처리
		if (command.equals("updateSubmit.mc")) {
			ms.memberUpdate(request, response);
		}

		// 회원정보 페이지 요청
		if (command.equals("info.mc")) {
			view = "/member/info.jsp";
		}
		// 로그아웃 요청 처리
		if (command.equals("logOut.mc")) {
			ms.logOut(request, response);
			request.setAttribute("test", "로그아웃 완료");
			view = "/common/main.jsp";
		}

		// 회원탈퇴 요청 페이지 호출
		if (command.equals("withdraw.mc")) {
			// 비밀번호를 다시 입력받아
			// 비밀번호가 일치할 경우 탈퇴 처리
			view = "/member/withdraw.jsp";
		}

		// 회원탈퇴 요청 처리
		if (command.equals("withdrawSubmit.mc")) {
			// 입력 받은 비밀번호가 일치할 경우 탈퇴 처리
			ms.withDraw(request, response);
		}

		/*
		 * 비밀번호 찾기
		 */
		if (command.equals("findPass.mc")) {
			// 아이디와 이름을 입력받아 일치하는 정보 일시
			// 메일 전송
			view = "/member/findPass.jsp";
		}

		if (command.equals("findPassSubmit.mc")) {
			// 비밀번호 찾기 메일 발송
			// 사용자 아이디로 등록된 메일로 코드 전송
			ms.findPassSubmit(request, response);
		}

		if (command.equals("passAccept.mc")) {
			// 아이디 와 code 를 전달 받아서 확인
			ms.changePassCode(request, response);
		}

		// 새로운 비밀번호로 변경
		if (command.equals("changePassSubmit.mc")) {
			ms.changePass(request, response);
		}

		if (view != null && !view.equals("")) {
			request.getRequestDispatcher(view).forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
