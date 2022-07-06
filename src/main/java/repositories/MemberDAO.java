package repositories;

import beans.MemberVO;

// DataBase Access Object
public interface MemberDAO {
	// 회원가입
	boolean memberJoin(MemberVO member);
	// 로그인 처리
	MemberVO memberLogin(String id, String pass);
	// 회원정보 수정
	boolean memberUpdate(MemberVO member);
	// cookie - id 값으로 사용자 정보 확인
	MemberVO getMemberById(String id);
	// 회원 탈퇴 처리 - joinYN
	void withDrawMember(int num);
	
	/**
	 * 비밀번호 찾기
	 */
	// 사용자 정보 확인
	boolean checkMember(String id, String name);
	
	// 코드 등록
	void addPassCode(String id, String code);
	
	// 코드 확인
	boolean checkPassCode(String id, String code);
	
	// 비밀번호 변경
	void changePass(String id, String pass);
}










