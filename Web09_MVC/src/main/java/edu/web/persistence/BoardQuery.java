package edu.web.persistence;

public interface BoardQuery {
	
	public static final String TABLE_NAME = "BOARD";
	
	public static final String COL_BOARD_ID = "BOARD_ID";
	public static final String COL_BOARD_TITLE = "BOARD_TITLE";
	public static final String COL_BOARD_CONTENT = "BOARD_CONTENT";
	public static final String COL_MEMBER_ID = "MEMBER_ID";
	public static final String COL_BOARD_DATE_CREATED = "BOARD_DATE_CREATED";
	
	//게시글을 등록할 수 있다.
	public static final String SQL_INSERT = "INSERT INTO " + TABLE_NAME + 
			" VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?, SYSDATE)";
	
	//게시글 아이디에 맞게 조회할 수 있다. 
	public static final String SQL_SELECT_BY_BOARD_ID = "SELECT * FROM " + TABLE_NAME + 
			" WHERE " + COL_BOARD_ID + " = ?"; 
	
	public static final String SQL_SELECT_PAGESCOPE = "SELECT * FROM (SELECT " + TABLE_NAME + 
			".*, ROW_NUMBER() OVER(ORDER BY " + COL_BOARD_ID + " DESC) RN FROM " + TABLE_NAME +
			") WHERE RN BETWEEN ? AND ?";
	
	public static final String SQL_TOTAL_CNT = "SELECT COUNT(" + COL_BOARD_ID + ") AS TOTAL_CNT FROM " + TABLE_NAME;
	
	//전체 조회할 수 있다.(+ 페이징 처리)
	public static final String SQL_SELECT_ALL = "SELECT * FROM " + TABLE_NAME + 
			" ORDER BY " + COL_BOARD_ID + " DESC";
	
	
	//게시글을 수정할 수 있다.
	public static final String SQL_UPDATE = "UPDATE " + TABLE_NAME + " SET " + 
			COL_BOARD_TITLE + " = ?, " + 
			COL_BOARD_CONTENT + " = ?, " + 
			COL_BOARD_DATE_CREATED + " = SYSDATE WHERE " + COL_BOARD_ID + " = ?";
	
	//게시글을 삭제할 수 있다.
	public static final String SQL_DELETE = "DELETE " + TABLE_NAME +
			" WHERE " + COL_BOARD_ID + " = ?";
	
	
}


