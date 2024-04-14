package edu.web.persistence;

import edu.web.domain.MemberVO;

public interface MemberDAO {
	
	String idDupChk(String memberId);
	
	int insertMember(MemberVO member);
	
	String loginChk(String memberId, String pw);
	
	int updateMember(MemberVO member);
	
	int deleteMember(String memberId);
}
	