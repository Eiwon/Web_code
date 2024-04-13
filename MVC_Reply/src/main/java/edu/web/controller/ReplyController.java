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
import edu.web.persistence.ReplyDAOImple;

@WebServlet("*.do")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String BOARD_URL = "WEB-INF/reply/";
	private static final String REPLY_LIST = "list";
    private static final String REPLY_REGISTER = "register";
    private static final String REPLY_DELETE = "delete";
    private static final String REPLY_UPDATE = "update";
    private static final String SERVER_EXTENSION = ".do";
    
    private ReplyDAOImple replyDao = null;
    
    public ReplyController() {
    	replyDao = ReplyDAOImple.getInstance();
    }
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String requestURI = request.getRequestURI();
		String requestMethod = request.getMethod();
		System.out.println("호출 경로 : " + requestURI);
		System.out.println("호출 방식 : " + requestMethod);
		
		if(requestURI.contains(REPLY_LIST + SERVER_EXTENSION)) {
			System.out.println("list 호출 확인");
			listGET(request, response);
		} else if(requestURI.contains(REPLY_REGISTER + SERVER_EXTENSION)) {
			System.out.println("register 호출 확인");
			registerPOST(request, response);
		} else if(requestURI.contains(REPLY_DELETE + SERVER_EXTENSION)) {
			System.out.println("delete 호출 확인");
			deletePOST(request, response);
		} else if(requestURI.contains(REPLY_UPDATE + SERVER_EXTENSION)) {
			System.out.println("update 호출 확인");
			updatePOST(request, response);
		}
		
    }

    private void listGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("replyListGET()");
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
	
	private void registerPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

	private void deletePOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("replyDeletePOST()");
		int replyId = Integer.parseInt(request.getParameter("replyId"));
		
		int res = replyDao.deleteReply(replyId);
		
		response.getWriter().write(String.valueOf(res));
		
	} // end replyDeletePOST
	
	private void updatePOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("replyUpdatePOST()");
		int replyId = Integer.parseInt(request.getParameter("replyId"));
		String replyContent = (String)request.getParameter("replyContent");
		
		int res = replyDao.updateReply(new ReplyVO(replyId, 0, null, replyContent, null));
		
		response.getWriter().write(String.valueOf(res));
		
	} // end replyUpdatePOST
}
