package edu.web.controller;

import java.io.IOException;
import java.nio.file.spi.FileSystemProvider;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.web.domain.BoardVO;
import edu.web.persistence.BoardDAOImple;
import edu.web.util.PageCriteria;
import edu.web.util.PageMaker;

@WebServlet("*.do") // *.do : ~.do로 선언된 HTTP 호출에 대해 반응
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String BOARD_URL = "WEB-INF/board/";
    
    private static final String BOARD = "/board/";
    
    private static final String MAIN = "index";
    private static final String LIST = "list";
    private static final String REGISTER = "register";
    private static final String DETAIL = "detail";
    private static final String UPDATE = "update";
    private static final String DELETE = "delete";
    
    private static final String EXTENSION = ".jsp";
    private static final String SERVER_EXTENSION = ".do";
    
    private BoardDAOImple boardDao = null;
    
    public BoardController() {
    	boardDao = BoardDAOImple.getInstance();    }

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
		
		
		// /login/login.jsp					/login/login.do
		// /login/register.jsp				/login/register.do
		// 									/login/idDupChk.do
		
		if(requestURI.contains(LIST + SERVER_EXTENSION)) {
			System.out.println("list 호출 확인");
			list(request, response);
		//}else if(request.getSession().getAttribute("memberId") != null) {
			// 세션 확인
		}else {
			routeBoard(request, response);
		}
		//}else {
		//	request.getRequestDispatcher(LOGIN_URL + LOGINN + EXTENSION)
		//					   .forward(request, response);
		//}
		
		
    } // end service

	private void routeBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String requestMethod = request.getMethod();
		
		if(requestURI.contains(REGISTER + SERVER_EXTENSION)) {
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
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("list()");
		String path = BOARD_URL + LIST + EXTENSION;
		String pageStr = request.getParameter("page");
		
		PageCriteria criteria = new PageCriteria();
		PageMaker pageMaker = new PageMaker();
		
		if(pageStr != null) {
			criteria.setPage(Integer.parseInt(pageStr)); 
		}
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(boardDao.getTotalCount());
		pageMaker.setPageData();
		System.out.println("전체 게시글 수 : " + pageMaker.getTotalCount());
		System.out.println("현재 선택된 페이지 : " + pageMaker.getCriteria().getPage());
		System.out.println("한 페이지당 게시글 수 : " + pageMaker.getCriteria().getNumsPerPage());
		System.out.println("페이지 링크 번호 개수 : " + pageMaker.getNumsOfPageLinks());
		System.out.println("시작 페이지 링크 번호 : " + pageMaker.getStartPageNo());
		System.out.println("끝 페이지 링크 번호 : " + pageMaker.getEndPageNo());
		//List<BoardVO> list = boardDao.select();
		
		List<BoardVO> list = boardDao.select(criteria);
		request.setAttribute("list", list);
		request.setAttribute("pageMaker", pageMaker);
		request.getRequestDispatcher(path).forward(request, response);
		
	} // end list
	
	private void registerGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("registerGET()");
		String path = BOARD_URL + REGISTER + EXTENSION;
		request.getRequestDispatcher(path).forward(request, response);
	} // end registerGET

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
	
	private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("detail()");
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		BoardVO board = boardDao.select(boardId);
		String path = BOARD_URL + DETAIL + EXTENSION;
		
		request.setAttribute("board", board);
		request.getRequestDispatcher(path).forward(request, response);
	} // end detail

	private void updateGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("updateGET()");
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		BoardVO board = boardDao.select(boardId);
		String path = BOARD_URL + UPDATE + EXTENSION;
		
		request.setAttribute("board", board);
		request.getRequestDispatcher(path).forward(request, response);
	} // end updateGET

	private void updatePOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("updatePOST()");
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		String boardTitle = request.getParameter("boardTitle");
		String boardContent = request.getParameter("boardContent");
		String path = MAIN + EXTENSION;
		
		int res = boardDao.update(new BoardVO(boardId, boardTitle, boardContent, null, null));
		
		if(res == 1) {
			//detail(request, response);
			path = DETAIL + SERVER_EXTENSION;
			response.sendRedirect(path + "?boardId=" + boardId);
		}else {
			response.sendRedirect(path);
		}
	} // end updatePOST

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

}




