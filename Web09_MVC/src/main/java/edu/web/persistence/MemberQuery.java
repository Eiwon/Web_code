package edu.web.persistence;

public interface MemberQuery {
	
	public static final String TABLE_MEMBER = "MEMBER";
	
	public static final String MEMBER_ID = "MEMBER_ID";
	public static final String PW = "PW";
	public static final String NAME = "NAME";
	public static final String EMAIL = "EMAIL";
	public static final String PHONE = "PHONE";
	
	// 아이디 중복 체크
	public static final String SELECT_ID_BY_ID = 
			"SELECT " + MEMBER_ID + " FROM " + TABLE_MEMBER + " WHERE "
			+ MEMBER_ID + " = ?";
	
	// 회원 등록
	public static final String INSERT_MEMBER = 
			"INSERT INTO " + TABLE_MEMBER + " VALUES(?, ?, ?, ?, ?)";
	
	// 로그인 정보 확인
	public static final String SELECT_ID_BY_ID_PW =
			"SELECT " + MEMBER_ID + " FROM " + TABLE_MEMBER + " WHERE "
			+ MEMBER_ID + " = ? AND " + PW + " = ?";
	
	// 회원 정보 수정
	public static final String UPDATE_MEMBER =
			"UPDATE " + TABLE_MEMBER + " SET " + PW + " = ?, " + NAME + 
			" = ?, " + EMAIL + " = ?, " + PHONE + " = ? WHERE " + MEMBER_ID
			+ " = ?";
	
	// 회원 탈퇴
	public static final String DELETE_MEMBER = 
			"DELETE " + TABLE_MEMBER + " WHERE " + MEMBER_ID + " = ?";
	
}
