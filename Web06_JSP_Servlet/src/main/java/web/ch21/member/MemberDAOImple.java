package web.ch21.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import oracle.jdbc.OracleDriver;


public class MemberDAOImple implements MemberDAO, DBConnection{

	private static MemberDAOImple instance = null;
	
	private MemberDAOImple() {}
	
	public static MemberDAOImple getInstance() {
		if(instance == null)
			instance = new MemberDAOImple();
		return instance;
	} // end getInstance
	
	@Override
	public int insert(MemberVO vo) {
		System.out.println("MemberDAOImple insert()");
		int res = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			DriverManager.registerDriver(new OracleDriver());
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");
			pstmt = conn.prepareStatement(SQL_INSERT);
			pstmt.setString(1, vo.getUserId());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getEmailAgree());
			pstmt.setString(5, vo.getInterestJoin());
			pstmt.setString(6, vo.getPhone());
			pstmt.setString(7, vo.getIntroduce());
			
			res = pstmt.executeUpdate();
		
			System.out.println(res + "행 등록");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return res;
	} // end insert

}






