package edu.web.board;

import java.sql.Timestamp;
import java.util.Date;

public class PostVO {
	private int postId;
	private String postTitle;
	private String postContent;
	private String userId;
	private int views;
	private Date writeDate;
	
	public PostVO() {}

	public PostVO(int postId, String postTitle, String postContent, String userId, int views, Date writeDate) {
		this.postId = postId;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.userId = userId;
		this.views = views;
		this.writeDate = writeDate;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public Timestamp getWriteTimestamp() {
		return new Timestamp(writeDate.getTime());
	} 
	
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	


}
