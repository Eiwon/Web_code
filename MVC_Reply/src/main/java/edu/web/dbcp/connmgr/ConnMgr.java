package edu.web.dbcp.connmgr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

// DBCP (DataBase Connection Pool)
//	- 브라우저에서 서버에 호출시 매번 DB 연결 객체를 생성하면 과부하 발생 위험
//	- 이를 해결하기 위해 다수의 연결을 컨트롤하는 기법
// 	원리
//	1) 웹 컨테이너가 실행되면서 Connection 객체를 미리 Pool에 생성
//	2) Pool에 저장된 Connection 객체를 필요할 때 쓰고 반환
//	3) 미리 생성하기 때문에 DB 부하 감소, 유동적으로 연결 관리

// DBCP 설정
// 1. ojdbc6.jar 파일을 \webapp\WEB-INF\lib에 저장
// 2. 톰캣 설치 경로\lib 폴더에 있는 tomcat-dbcp.jar 파일을 
//		웹 프로젝트 경로 \webapp\WEB-INF\lib에 저장
// 3. \webapp\META-INF\context.xml 파일에 Resource 태그 추가



public class ConnMgr {
	
	public static Connection getConnection() throws Exception{
		// throws Exception : 호출한 쪽에서 try catch로 에러 처리해야함
		Connection conn = null;
		
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:comp/env");
		DataSource ds = (DataSource) envContext.lookup("dbcp/orcl");
		conn = ds.getConnection();
		System.out.println("연결 성공");
		
		return conn;
	}

	public static void close(Connection conn, PreparedStatement pstmt) {
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
}
















