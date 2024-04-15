package edu.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.web.domain.ReplyVO;
import edu.web.persistence.ReplyDAO;
import edu.web.persistence.ReplyDAOImple;
import edu.web.util.PageCriteria;
import edu.web.util.PageMaker;

@WebServlet("/replies/*")
public class ReplyController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private static final String LIST = "list";
	private static final String ADD = "add";
	private static final String UPDATE = "update";
	private static final String DELETE = "delete";
	private ReplyDAO replyDao = null;
	
	//		/replies/list
	// 									/replies/add
	//									/replies/update
	//									/replies/delete
	
	public ReplyController() {
		replyDao = ReplyDAOImple.getInstance();
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		System.out.println("호출 URI : " + requestURI);
		
		
		if(requestURI.contains(ADD)) {
			System.out.println("add 호출 확인");
			replyAdd(request, response);
		} else if(requestURI.contains(LIST)) {
			System.out.println("list 호출 확인");
			listPOST(request, response);
		} else if(requestURI.contains(UPDATE)) {
			System.out.println("update 호출 확인");
			updatePOST(request, response); 
		} else if(requestURI.contains(DELETE)) {
			System.out.println("delete 호출 확인");
			deletePOST(request, response);
		}
	}
	
	public void listPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("replyListPOST()");
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		String page = request.getParameter("page");
		PageCriteria criteria = new PageCriteria();
		
		if(page != null) {
			criteria.setPage(Integer.parseInt(page));;
		}
		
		List<ReplyVO> list = replyDao.selectPage(boardId, criteria);
		
		JSONArray jsonArr = new JSONArray();
		
		for(ReplyVO reply : list) {
			JSONObject obj = new JSONObject();
			obj.put("replyId", reply.getReplyId());
			obj.put("boardId", reply.getBoardId());
			obj.put("memberId", reply.getMemberId());
			obj.put("replyContent", reply.getReplyContent());
			obj.put("replyDateCreated", reply.getReplyDateCreated().toString());
			jsonArr.add(obj);
		}
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(replyDao.getTotalCount(boardId));
		pageMaker.setPageData();
		JSONObject jsonPM = new JSONObject();
		jsonPM.put("startPageNo", pageMaker.getStartPageNo());
		jsonPM.put("endPageNo", pageMaker.getEndPageNo());
		jsonPM.put("hasPrev", pageMaker.isHasPrev());
		jsonPM.put("hasNext", pageMaker.isHasNext());
		System.out.println("전체 게시글 수 : " + pageMaker.getTotalCount());
		System.out.println("현재 선택된 페이지 : " + pageMaker.getCriteria().getPage());
		System.out.println("한 페이지당 게시글 수 : " + pageMaker.getCriteria().getNumsPerPage());
		System.out.println("페이지 링크 번호 개수 : " + pageMaker.getNumsOfPageLinks());
		System.out.println("시작 페이지 링크 번호 : " + pageMaker.getStartPageNo());
		System.out.println("끝 페이지 링크 번호 : " + pageMaker.getEndPageNo());
		JSONObject result = new JSONObject();
		
		result.put("pageMaker", jsonPM.toString());
		result.put("replyList", jsonArr.toString());
		
		response.getWriter().write(result.toString());
		
	} // end replyListGET
	
	// ajax 통신으로 댓글 JSON 데이터를 전송받아
	// DB에 저장하고, 성공시 success 메시지를 돌려줌
	public void replyAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject obj = null;
		try {
			obj = (JSONObject)new JSONParser().parse(request.getParameter("reply"));
		} catch (ParseException e) {
			System.out.println(e.toString());
		}
		ReplyVO reply = new ReplyVO(0, Integer.parseInt(obj.get("boardId").toString()), 
				(String)obj.get("memberId"), (String)obj.get("replyContent"), null);
		
		int res = replyDao.insertReply(reply);
		
		response.getWriter().write(String.valueOf(res));
		
	} // end replyRegisterPOST

	public void deletePOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("replyDeletePOST()");
		int replyId = Integer.parseInt(request.getParameter("replyId"));
		
		int res = replyDao.deleteReply(replyId);
		
		response.getWriter().write(String.valueOf(res));
		
	} // end replyDeletePOST
	
	public void updatePOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("replyUpdatePOST()");
		int replyId = Integer.parseInt(request.getParameter("replyId"));
		String replyContent = (String)request.getParameter("replyContent");
		
		int res = replyDao.updateReply(new ReplyVO(replyId, 0, null, replyContent, null));
		
		response.getWriter().write(String.valueOf(res));
		
	} // end replyUpdatePOST
}
