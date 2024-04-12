package edu.web.persistence;

import java.util.List;

import edu.web.domain.ReplyVO;

public interface ReplyDAO {
	
	int insertReply(ReplyVO reply);
	
	List<ReplyVO> selectByBoardId(int boardId);
	
	int updateReply(ReplyVO reply);
	
	int deleteReply(int replyId);	
}
