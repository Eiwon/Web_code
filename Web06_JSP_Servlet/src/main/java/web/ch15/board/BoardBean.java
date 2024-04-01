package web.ch15.board;

import java.io.Serializable;
import java.util.Date;

// 게시글 데이터 ( 번호, 제목, 작성자, 작성일)를 전송하기 위한 Bean 클래스
public class BoardBean implements Serializable{
	private int no;
	private String title;
	private String content;
	private String writer;
	private Date regDate;
	
	public BoardBean() {}
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	
}
