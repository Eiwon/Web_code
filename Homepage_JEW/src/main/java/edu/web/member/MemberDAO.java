package edu.web.member;

public interface MemberDAO {
	
	int insert(MemberVO vo);
	
	MemberVO selectByUserId(String userId);
	
	String selectPwById(String userId, String pw);
	
	int update(MemberVO vo);
	
	int delete(String userId);
	
}
