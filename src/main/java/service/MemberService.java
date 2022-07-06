package service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.MemberVO;
import repositories.MemberDAO;
import repositories.MemberDAOImpl;

public interface MemberService {
	
	// 회원가입 처리
	void memberJoin(HttpServletRequest request,
					HttpServletResponse response);

	// 로그인 처리
	/**
	 * @return true - 로그인 성공
	 * @return false - 로그인 실패
	 */
	boolean memberLogin(HttpServletRequest request,
					    HttpServletResponse response);
	
	// 회원정보 수정
	void memberUpdate(HttpServletRequest request,
					  HttpServletResponse response);
	
	// 로그아웃
	void logOut(HttpServletRequest request,
			    HttpServletResponse response);
	
	// 회원탈퇴 
	void withDraw(HttpServletRequest request,
				  HttpServletResponse response);
	
	/**
	 * 비밀번호 찾기
	 * @param request - id(email), name(사용자 이름)
	 * @param response - 요청 정보에 따라 화면 전환
	 */
	void findPassSubmit(HttpServletRequest request,
					    HttpServletResponse response);
	/**
	 * 비밀번호 변경 요청 정보 확인
	 * @param request - id(email) , code
	 * @param response - 비밀번호 변경 페이지, 실패
	 */
	void changePassCode(HttpServletRequest request,
						HttpServletResponse response);
	
	/**
	 * @param request - id(email), code , pass
	 * @param response - 비밀번호 변경 여부에 따라 응답
	 */
	void changePass(HttpServletRequest request,
					HttpServletResponse response);
	
	static void loginCheck(HttpServletRequest request) {
		System.out.println("login cookie check 요청");
		HttpSession session = request.getSession();
		if(session.getAttribute("member") == null) {
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				for(Cookie c : cookies) {
					if(c.getName().equals("id")) {
						String id = c.getValue();
						MemberDAO dao = new MemberDAOImpl();
						MemberVO member = dao.getMemberById(id);
						if(member != null) {
							session.setAttribute("member", member);
						}
						break;
					}
				}
			}
		}
		
	}
 }








