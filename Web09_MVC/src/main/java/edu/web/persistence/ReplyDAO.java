package edu.web.persistence;

import java.util.List;

import edu.web.domain.ReplyVO;
import edu.web.util.PageCriteria;

public interface ReplyDAO {
	
	int insertReply(ReplyVO reply);
	
	List<ReplyVO> selectByBoardId(int boardId);
	
	List<ReplyVO> selectPage(int boardId, PageCriteria criteria);
	
	int getTotalCount(int boardId);
	
	int updateReply(ReplyVO reply);
	
	int deleteReply(int replyId);	
}
