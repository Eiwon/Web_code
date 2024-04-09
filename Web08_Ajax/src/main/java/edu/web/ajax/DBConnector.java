package edu.web.ajax;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import oracle.jdbc.OracleDriver;

public class DBConnector {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "scott";
	private static final String PASSWORD = "tiger";
	
	private static final String SQL_SELECT_BY_TITLE = "SELECT * FROM SEARCH WHERE TITLE LIKE ?";
	
	private static DBConnector instance = null;
	
	private DBConnector() {}
	
	public static DBConnector getInstance() {
		if(instance == null)
			instance = new DBConnector();
		return instance;
	}
	
	public List<SearchVO> selectByTitle(String target){
		System.out.println("DBConnector selectByTitle()");
		List<SearchVO> result = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			DriverManager.registerDriver(new OracleDriver());
			pstmt = DriverManager.getConnection(URL, USER, PASSWORD)
					.prepareStatement(SQL_SELECT_BY_TITLE);
			System.out.println("드라이버 연결 성공");
			pstmt.setString(1, '%' + target + '%');
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				result.add(new SearchVO(rs.getInt(1), rs.getString(2)));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	} // end selectByTitle
	
}
