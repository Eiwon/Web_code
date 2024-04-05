package edu.web.board;

import java.util.List;

public interface BoardDAO {
	
	List<PostVO> selectAllPost(int page);
	
	PostVO selectPostById(int postId);
	
	List<PostVO> selectTitlePost(int page, String title);
	
	int insertPost(PostVO post);
	
	int updatePostById(PostVO post);
	
	int deletePostById(int postId);
}
