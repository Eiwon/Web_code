package edu.web.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleDriver;

public class BoardDAOImple implements BoardDAO, SQLBoardQuery{
	
	private static BoardDAOImple instance = null;
	
	private BoardDAOImple() {}
	
	public static BoardDAOImple getInstance() {
		if(instance == null)
			instance = new BoardDAOImple();
		
		return instance;
	}

	@Override
	public List<PostVO> selectAllPost(int page) {
		System.out.println("selectAllPost()");
		List<PostVO> result = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			DriverManager.registerDriver(new OracleDriver());
			pstmt = DriverManager.getConnection(URL, USER, PASSWORD)
					.prepareStatement(SELECT_ALL_POST);
			pstmt.setInt(1, (page-1) *5 +1);
			pstmt.setInt(2, page *5);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				result.add(new PostVO(
						rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getInt(5), rs.getDate(6)
						));
			}
			System.out.println("읽어온 데이터 수 : " + result.size());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResource(pstmt, rs);
		}
		return result;
	} // end selectAllPost
	
	@Override
	public PostVO selectPostById(int postId) {
		System.out.println("selectPostById()");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PostVO result = null;
		
		try {
			DriverManager.registerDriver(new OracleDriver());
			pstmt = DriverManager.getConnection(URL, USER, PASSWORD).prepareStatement(SELECT_BY_PID);
			pstmt.setInt(1, postId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = new PostVO(rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getInt(5), rs.getDate(6));
			}
			if(result != null) {
				System.out.println(result.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResource(pstmt, rs);
		}
		
		return result;
	} // end selectPostById
	
	@Override
	public List<PostVO> selectTitlePost(int page, String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertPost(PostVO post) {
		System.out.println("insertPost()");
		int res = 0;
		PreparedStatement pstmt = null;
		try {
			DriverManager.registerDriver(new OracleDriver());
			pstmt = DriverManager.getConnection(URL, USER, PASSWORD)
			.prepareStatement(INSERT_POST);
			
			pstmt.setString(1, post.getPostTitle());
			pstmt.setString(2, post.getPostContent());
			pstmt.setString(3, post.getUserId());
			pstmt.setTimestamp(4, post.getWriteTimestamp());
			
			res = pstmt.executeUpdate();
			
			System.out.println(res + "행 추가 성공");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResource(pstmt);
		}
		
		return res;
	} // end insertPost

	@Override
	public int updatePostById(PostVO post) {
		System.out.println("updatePostById()");
		int res =0;
		PreparedStatement pstmt = null;
		try {
			DriverManager.registerDriver(new OracleDriver());
			pstmt = DriverManager.getConnection(URL, USER, PASSWORD)
			.prepareStatement(UPDATE_POST_BY_ID);
			
			pstmt.setString(1, post.getPostTitle());
			pstmt.setString(2, post.getPostContent());
			pstmt.setInt(3, post.getPostId());
			
			res = pstmt.executeUpdate();
			
			System.out.println(res + "행 수정 성공");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResource(pstmt);
		}
		
		return res;
	} // end updatePostById

	@Override
	public int deletePostById(int postId) {
		System.out.println("deletePostById()");
		int res = 0;
		PreparedStatement pstmt = null;
		try {
			DriverManager.registerDriver(new OracleDriver());
			pstmt = DriverManager.getConnection(URL, USER, PASSWORD)
			.prepareStatement(DELETE_POST_BY_ID);
			
			pstmt.setInt(1, postId);
			res = pstmt.executeUpdate();
			
			System.out.println(res + "행 삭제 성공");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResource(pstmt);
		}
		
		return res;
	} // end deletePostById
	
	private void closeResource(PreparedStatement pstmt, ResultSet rs) {
		try {
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("리소스 해제 실패");
		}
	}
	private void closeResource(PreparedStatement pstmt) {
		try {
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("리소스 해제 실패");
		}
	}
}
