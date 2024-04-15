package edu.web.persistence;

public interface ReplyQuery {
	
	public static final String TABLE_NAME = "REPLY";
	
	public static final String REPLY_ID = "REPLY_ID";
	public static final String BOARD_ID = "BOARD_ID";
	public static final String MEMBER_ID = "MEMBER_ID";
	public static final String REPLY_CONTENT = "REPLY_CONTENT";
	public static final String REPLY_DATE_CREATED = "REPLY_DATE_CREATED";
	
	// 댓글 추가
	public static final String INSERT_REPLY
		= "INSERT INTO " + TABLE_NAME + " VALUES(REPLY_SEQ.NEXTVAL, ?, ?, ?, SYSDATE)";
	
	// 댓글 조회
	public static final String SELECT_BY_BOARD_ID
		= "SELECT * FROM " + TABLE_NAME + " WHERE " + BOARD_ID + 
		" = ? ORDER BY " + REPLY_ID + " DESC";
	
	public static final String SELECT_PAGESCORP
		= "SELECT * FROM (SELECT " + TABLE_NAME + ".*, ROW_NUMBER() OVER (ORDER BY " + REPLY_ID 
	+ " DESC) AS RN FROM " + TABLE_NAME + " WHERE " + BOARD_ID + " = ?) WHERE RN BETWEEN ? AND ?";
	
	public static final String TOTAL_CTN
		= "SELECT COUNT(" + REPLY_ID + ") FROM " + TABLE_NAME + " WHERE " + BOARD_ID + " = ?";
	
	// 댓글 수정
	public static final String UPDATE_REPLY
		= "UPDATE " + TABLE_NAME + " SET " + REPLY_CONTENT + " = ?, " + REPLY_DATE_CREATED
			+ " = SYSDATE WHERE " + REPLY_ID + " = ?";
	
	// 댓글 삭제
	public static final String DELETE_REPLY
		= "DELETE " + TABLE_NAME + " WHERE " + REPLY_ID + " = ?";
	
	
	
}
