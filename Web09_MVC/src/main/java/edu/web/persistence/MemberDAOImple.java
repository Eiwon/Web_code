package edu.web.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import edu.web.dbcp.connmgr.ConnMgr;
import edu.web.domain.MemberVO;

public class MemberDAOImple implements MemberDAO, MemberQuery{

	private static MemberDAOImple instance = null;
	
	private MemberDAOImple() {}
	
	public static MemberDAOImple getInstance() {
		if(instance == null) {
			instance = new MemberDAOImple();
		}
		return instance;
	}

	@Override
	public String idDupChk(String memberId) {
		System.out.println("idDupChk()");
		String result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnMgr.getConnection();
			pstmt = conn.prepareStatement(SELECT_ID_BY_ID);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getString(1);
			}
			System.out.println("idDupChk result : " + result);
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			ConnMgr.close(conn, pstmt, rs);
		}
		
		return result;
	} // end idDupChk

	@Override
	public int insertMember(MemberVO member) {
		System.out.println("insertMember()");
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ConnMgr.getConnection();
			pstmt = conn.prepareStatement(INSERT_MEMBER);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getPw());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getPhone());
			
			result = pstmt.executeUpdate();
			
			System.out.println(result + "행 추가 성공");
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}finally {
			ConnMgr.close(conn, pstmt);
		}
		
		return result;
	} // end insertMember

	@Override
	public String loginChk(String memberId, String pw) {
		System.out.println("loginChk()");
		String result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnMgr.getConnection();
			pstmt = conn.prepareStatement(SELECT_ID_BY_ID_PW);
			pstmt.setString(1, memberId);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getString(1);
			}
			System.out.println("idDupChk result : " + result);
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			ConnMgr.close(conn, pstmt, rs);
		}
		
		return result;
	} // end loginChk

	@Override
	public int updateMember(MemberVO member) {
		System.out.println("updateMember()");
		
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ConnMgr.getConnection();
			pstmt = conn.prepareStatement(UPDATE_MEMBER);
			pstmt.setString(1, member.getPw());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getMemberId());
			
			result = pstmt.executeUpdate();
			
			System.out.println(result + "행 수정 성공");
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}finally {
			ConnMgr.close(conn, pstmt);
		}
		
		return result;
		
	} // end updateMember

	@Override
	public int deleteMember(String memberId) {
		System.out.println("deleteMember()");
		
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ConnMgr.getConnection();
			pstmt = conn.prepareStatement(DELETE_MEMBER);
			pstmt.setString(1, memberId);
			
			result = pstmt.executeUpdate();
			
			System.out.println(result + "행 삭제 성공");
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}finally {
			ConnMgr.close(conn, pstmt);
		}
		
		return result;
	} // end deleteMember
	
	
	
}
