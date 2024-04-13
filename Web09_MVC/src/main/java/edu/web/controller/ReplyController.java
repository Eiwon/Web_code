package edu.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.web.domain.ReplyVO;
import edu.web.persistence.ReplyDAO;
import edu.web.persistence.ReplyDAOImple;

public class ReplyController {
	
	private static ReplyController instance = null;
	private ReplyDAO replyDao = null;
	
	private ReplyController() {
		replyDao = ReplyDAOImple.getInstance();
	}
	
	public static ReplyController getInstance() {
		if(instance == null)
			instance = new ReplyController();
		return instance;
	} // end getInstance

	public void listPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("replyListPOST()");
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		
		List<ReplyVO> list = replyDao.selectByBoardId(boardId);
		
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
		
		response.getWriter().write(jsonArr.toString());
		
	} // end replyListGET
	
	public void registerPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
