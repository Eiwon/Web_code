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
	
	public BoardDAOImple getInstance() {
		if(instance == null)
			instance = new BoardDAOImple();
		
		return instance;
	}

	@Override
	public List<PostVO> selectAllPost(int page) {
		System.out.println("selectAllPost()");
		List<PostVO> result = new ArrayList<>();
		PreparedStatement pstmt = null;
		
		try {
			DriverManager.registerDriver(new OracleDriver());
			pstmt = DriverManager.getConnection(URL, USER, PASSWORD)
					.prepareStatement(SELECT_ALL_POST);
			
			pstmt.setInt(1, (page-1) *20 +1);
			pstmt.setInt(2, page *20);
			
			ResultSet rs = pstmt.executeQuery();
			
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
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	@Override
	public List<PostVO> selectTitlePost(int page, String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertPost(PostVO post) {
		int res;
		PreparedStatement pstmt = null;
		try {
			DriverManager.registerDriver(new OracleDriver());
			pstmt = DriverManager.getConnection(URL, USER, PASSWORD)
			.prepareStatement(INSERT_POST);
			
			pstmt.setInt(1, post.getPostId());
			pstmt.setString(2, post.getPostTitle());
			pstmt.setString(3, post.getPostContent());
			pstmt.setString(4, post.getUserId());
			pstmt.setInt(5, post.getViews());
			pstmt.setTimestamp(6, post.getWriteTimestamp());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int updatePostById(PostVO post) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deletePostById(int postId) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
}
