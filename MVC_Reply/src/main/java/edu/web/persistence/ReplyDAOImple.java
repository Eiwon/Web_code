package edu.web.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.web.dbcp.connmgr.ConnMgr;
import edu.web.domain.ReplyVO;

public class ReplyDAOImple implements ReplyDAO, ReplyQuery {

	private static ReplyDAOImple instance = null;
	
	private ReplyDAOImple() {};
	
	public static ReplyDAOImple getInstance() {
		if(instance == null)
			instance = new ReplyDAOImple();
		return instance;
	}
	
	@Override
	public int insertReply(ReplyVO reply) {
		System.out.println("insertReply()");
		int res = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ConnMgr.getConnection();
			pstmt = conn.prepareStatement(INSERT_REPLY);
			pstmt.setInt(1, reply.getBoardId());
			pstmt.setString(2, reply.getMemberId());
			pstmt.setString(3, reply.getReplyContent());
			
			res = pstmt.executeUpdate();
			
			System.out.println(res + "행 추가 성공");
			
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			ConnMgr.close(conn, pstmt);
		}
		
		return res;
	}

	@Override
	public List<ReplyVO> selectByBoardId(int boardId) {
		System.out.println("selectByBoardId()");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ReplyVO> list = new ArrayList<>();
		
		try {
			conn = ConnMgr.getConnection();
			pstmt = conn.prepareStatement(SELECT_BY_BOARD_ID);
			pstmt.setInt(1, boardId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(new ReplyVO(rs.getInt(1), rs.getInt(2), rs.getString(3), 
						rs.getString(4), rs.getDate(5)));
			}
			System.out.println(list.toString());
			
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			ConnMgr.close(conn, pstmt, rs);
		}
		
		return list;
	}

	@Override
	public int updateReply(ReplyVO reply) {
		int res = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ConnMgr.getConnection();
			pstmt = conn.prepareStatement(UPDATE_REPLY);
			pstmt.setString(1, reply.getReplyContent());
			pstmt.setInt(2, reply.getReplyId());
			
			res = pstmt.executeUpdate();
			System.out.println(res + "행 수정 성공");
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			ConnMgr.close(conn, pstmt);
		}
		return res;
	}

	@Override
	public int deleteReply(int replyId) {
		int res = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ConnMgr.getConnection();
			pstmt = conn.prepareStatement(DELETE_REPLY);
			pstmt.setInt(1, replyId);
			
			res = pstmt.executeUpdate();
			System.out.println(res + "행 삭제 성공");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	
		return res;
	}

	
}
