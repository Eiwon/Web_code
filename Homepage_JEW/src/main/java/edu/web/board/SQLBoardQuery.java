package edu.web.board;

public interface SQLBoardQuery {
	// DB 접속에 필요한 상수 정의
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	public static final String USER = "scott";
	public static final String PASSWORD = "tiger";
	
	public static final String TABLE_POST = "TEST_POST";
	public static final String TABLE_REPLY = "TEST_REPLY";
	
	public static final String COL_POST_ID = "POST_ID";
	public static final String COL_POST_TITLE = "POST_TITLE";
	public static final String COL_POST_CONTENT = "POST_CONTENT";
	public static final String COL_USERID = "USERID";
	public static final String COL_VIEWS = "VIEWS";
	public static final String COL_WRITE_DATE = "WRITE_DATE";
	
	public static final String COL_REPLY_ID = "REPLY_ID";
	public static final String COL_REPLY_CONTENT = "REPLY_CONTENT";
	
	// 게시글 20개 단위로 검색
	public static final String SELECT_ALL_POST = "SELECT * FROM (" + TABLE_POST + 
			".*, ROW_NUMBER() OVER (ORDER BY " + COL_POST_ID + ") AS RN FROM " + TABLE_POST
			+ ") WHERE RN BETWEEN ? AND ?";
	
	// 입력값을 제목에 포함하는 게시글 20개 단위로 검색
	public static final String SLELCT_TITLE_POST = "SELECT * FROM ( " + TABLE_POST + 
			".*, ROW_NUMBER() OVER (ORDER BY " + COL_POST_ID + ") AS RN FROM " + TABLE_POST
			+ " WHERE " + COL_POST_TITLE + " LIKE ?) WHERE RN BETWEEN ? AND ?";
	
	// 게시글 ID로 검색
	public static final String SELECT_BY_PID = "SELECT * FROM " + TABLE_POST + " WHERE "
			+ COL_POST_ID + " = ?";
	
	// 게시글 추가
	public static final String INSERT_POST = "INSERT INTO " + TABLE_POST + 
			" VALUES (?, ?, ?, ?, ?, ?)";
	
	// 게시글 수정
	public static final String UPDATE_POST_BY_ID = "UPDATE " + TABLE_POST + " SET "
			+ COL_POST_TITLE + " = ?, " + COL_POST_CONTENT + " = ?, " + COL_USERID + " = ?, " 
			+ COL_VIEWS + " = ?, " + COL_WRITE_DATE + " = ? WHERE " + COL_POST_ID + " = ?";
	
	// 게시글 삭제
	public static final String DELETE_POST_BY_ID = "DELETE " + TABLE_POST + " WHERE " + 
			COL_POST_ID + " = ?";
	
}




