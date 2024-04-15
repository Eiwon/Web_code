package edu.web.persistence;

import java.util.List;

import edu.web.domain.BoardVO;
import edu.web.util.PageCriteria;

public interface BoardDAO {
	
	//게시글을 등록할 수 있다.
	int insert(BoardVO vo);
	
	//게시글 아이디에 맞게 조회할 수 있다. 
	BoardVO select(int boardId);
	
	//전체 조회할 수 있다.(+ 페이징 처리)
	List<BoardVO> select();
	
	List<BoardVO> select(PageCriteria criteria);
	
	int getTotalCount();
	
	//게시글을 수정할 수 있다.
	int update(BoardVO vo);
	
	//게시글을 삭제할 수 있다.
	int delete(int boardId);
}
