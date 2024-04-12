package edu.web.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import edu.web.domain.BoardVO;
import edu.web.domain.ReplyVO;
import edu.web.persistence.BoardDAOImple;
import edu.web.persistence.ReplyDAOImple;

@WebServlet("*.do") // *.do : ~.do로 선언된 HTTP 호출에 대해 반응
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String BOARD_URL = "WEB-INF/board/";
    private static final String MAIN = "index";
    private static final String LIST = "list";
    private static final String REGISTER = "register";
    private static final String DETAIL = "detail";
    private static final String UPDATE = "update";
    private static final String DELETE = "delete";
    private static final String EXTENSION = ".jsp";
    private static final String SERVER_EXTENSION = ".do";
	
    private static final String REPLY_LIST = "replyList";
    private static final String REPLY_REGISTER = "replyRegister";
    private static final String REPLY_DELETE = "replyDelete";
    private static final String REPLY_UPDATE = "replyUpdate";
    
    private BoardDAOImple boardDao = null;
    private ReplyDAOImple replyDao = null;
    
    public BoardController() {
    	boardDao = BoardDAOImple.getInstance();
    	replyDao = ReplyDAOImple.getInstance();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String requestURI = request.getRequestURI();
		String requestMethod = request.getMethod();
		System.out.println("호출 경로 : " + requestURI);
		System.out.println("호출 방식 : " + requestMethod);
		
		// GET								POST
		// /board/list.jsp					/board/register.do
		// /board/register.jsp				/board/update.do
		// /board/detail.jsp				/board/delete.do
		// /board/update.jsp				jsp는 do로 바꾸고 GET, POST로 구분
		
		if(requestURI.contains(LIST + SERVER_EXTENSION)) {
			System.out.println("list 호출 확인");
			list(request, response);
		}else if(requestURI.contains(REGISTER + SERVER_EXTENSION)) {
			System.out.println("register 호출 확인");
			if(requestMethod.equals("GET")) { // 페이지 불러오기
				registerGET(request, response);
			}else if(requestMethod.equals("POST")){ // DB에 저장
				registerPOST(request, response);
			}
		} else if(requestURI.contains(DETAIL + SERVER_EXTENSION)) {
			System.out.println("detail 호출 확인");
			detail(request, response);
		} else if(requestURI.contains(UPDATE + SERVER_EXTENSION)) {
			System.out.println("update 호출 확인");
			if(requestMethod.equals("GET")) {
				updateGET(request, response);
			} else if(requestMethod.equals("POST")) {
				updatePOST(request, response);
			}
		} else if(requestURI.contains(DELETE + SERVER_EXTENSION)) {
			System.out.println("delete 호출 확인");
			if(requestMethod.equals("POST")) {
				deletePOST(request, response);
			}
		} else if(requestURI.contains(REPLY_LIST + SERVER_EXTENSION)) {
			System.out.println("replyList 호출 확인");
			replyListGET(request, response);
		} else if(requestURI.contains(REPLY_REGISTER + SERVER_EXTENSION)) {
			System.out.println("replyRegister 호출 확인");
			replyRegisterPOST(request, response);
		} else if(requestURI.contains(REPLY_DELETE + SERVER_EXTENSION)) {
			System.out.println("replyDelete 호출 확인");
			replyDeletePOST(request, response);
		} else if(requestURI.contains(REPLY_UPDATE + SERVER_EXTENSION)) {
			System.out.println("replyUpdate 호출 확인");
			replyUpdatePOST(request, response);
		}
		
		
    } // end service

	// TODO : 전체 게시판 내용(list)을 DB에서 가져오고, 그 데이터를 list.jsp 페이지에 전송
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("list()");
		String path = "/" + BOARD_URL + LIST + EXTENSION;
		List<BoardVO> list = boardDao.select();
		
		request.setAttribute("list", list);
		getServletContext().getRequestDispatcher(path).forward(request, response);
	} // end list
	
	// TODO : register.jsp 페이지 호출
	private void registerGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("registerGET()");
		String path = "/" + BOARD_URL + REGISTER + EXTENSION;
		getServletContext().getRequestDispatcher(path).forward(request, response);
	} // end registerGET

	// TODO : register.jsp form으로부터 전송된 데이터를 DB 테이블에 등록
	// TODO : index.jsp로 이동
	private void registerPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("registerPOST()");
		
		String boardTitle = request.getParameter("boardTitle");
		String boardContent = request.getParameter("boardContent");
		String memberId = request.getParameter("memberId");
		BoardVO board = new BoardVO(0, boardTitle, boardContent, memberId, null);
		String path = MAIN + EXTENSION;
		int res = boardDao.insert(board);
		
		if(res == 1) {
			System.out.println("registerPOST() 등록 성공");
		} else {
			System.out.println("registerPOST() 등록 실패");
		}
		response.sendRedirect(path);
	} // end registerPOST
	
	// TODO : DB 테이블에서 상세 조회 데이터를 가져와서 detail.jsp 페이지로 전송
	private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("detail()");
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		BoardVO board = boardDao.select(boardId);
		String path = "/" + BOARD_URL + DETAIL + EXTENSION;
		
		request.setAttribute("board", board);
		getServletContext().getRequestDispatcher(path).forward(request, response);
	} // end detail

	// TODO : DB 테이블에서 상세 조회한 게시글 데이터를 전송하고, update.jsp 페이지 호출
	private void updateGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("updateGET()");
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		BoardVO board = boardDao.select(boardId);
		String path = "/" + BOARD_URL + UPDATE + EXTENSION;
		
		request.setAttribute("board", board);
		getServletContext().getRequestDispatcher(path).forward(request, response);
	} // end updateGET

	// TODO : update.jsp에서 전송된 수정할 데이터를 DB로 전송하여 테이블 수정 수행
	// TODO : 수정 완료되면, detail.jsp로 이동
	private void updatePOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("updatePOST()");
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		String boardTitle = request.getParameter("boardTitle");
		String boardContent = request.getParameter("boardContent");
		String path = MAIN + EXTENSION;
		
		int res = boardDao.update(new BoardVO(boardId, boardTitle, boardContent, null, null));
		
		if(res == 1) {
			detail(request, response);
		}else {
			response.sendRedirect(path);
		}
	} // end updatePOST

	// TODO : 게시글 번호를 전송받아, DB 테이블에서 데이터 삭제
	// TODO : 삭제가 완료되면, index.jsp로 이동
	private void deletePOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("deletePOST()");
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		int res = boardDao.delete(boardId);
		String path = MAIN + EXTENSION;
		
		if(res == 1) {
			response.sendRedirect(path);
		}else {
			response.sendRedirect(path);
		}
	} // end deletePOST


	private void replyListGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	
	private void replyRegisterPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

	private void replyDeletePOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("replyDeletePOST()");
		int replyId = Integer.parseInt(request.getParameter("replyId"));
		
		int res = replyDao.deleteReply(replyId);
		
		response.getWriter().write(String.valueOf(res));
		
	} // end replyDeletePOST
	
	private void replyUpdatePOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("replyUpdatePOST()");
		int replyId = Integer.parseInt(request.getParameter("replyId"));
		String replyContent = (String)request.getParameter("replyContent");
		
		int res = replyDao.updateReply(new ReplyVO(replyId, 0, null, replyContent, null));
		
		response.getWriter().write(String.valueOf(res));
		
	} // end replyUpdatePOST
	
}




