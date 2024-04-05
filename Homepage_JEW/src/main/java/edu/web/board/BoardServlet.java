package edu.web.board;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/list")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BoardDAO boardDao;
    public BoardServlet() {	
    	boardDao = BoardDAOImple.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() : " + request.getRequestURI());
		String reqPId = request.getParameter("postId");
		
		if(request.getParameter("type").equals("detail")) {
			// postId에 해당하는 게시글 클릭 이벤트
			PostVO post = boardDao.selectPostById(Integer.parseInt(reqPId));
			if(post != null) {
				System.out.println("BoardServlet doGet() success selectPostById()");
				request.setAttribute("postInfo", post);
				getServletContext().getRequestDispatcher("/board/readPost.jsp")
								   .forward(request, response);
			}else {
				System.out.println("BoardServlet doGet() failed selectPostById()");
			}
			
		}else if(request.getParameter("type").equals("search")){ // 게시글 검색 이벤트
			int page = 1;
			if(request.getParameter("page") != null) { // 지정 페이지 검색
				page = Integer.parseInt(request.getParameter("page")); 
			}
			List<PostVO> list = boardDao.selectAllPost(page);
			request.setAttribute("postList", list);
			getServletContext().getRequestDispatcher("/board/boardMain.jsp")
							   .forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardServlet doPost()");
		System.out.println("request type : " + request.getParameter("type"));
		
		String type = request.getParameter("type");
		String postTitle = request.getParameter("postTitle");
		String postContent = request.getParameter("postContent");
		String userId = (String) request.getSession().getAttribute("userId");

		if(type.equals("등록")) {
			PostVO vo = new PostVO(0, postTitle, postContent, userId, 0, new Date());

			int res = boardDao.insertPost(vo);

			if (res == 1) {
				System.out.println("글 추가 성공");
				response.getWriter().print("<script>alert('글 추가 성공');</script>");
			} else {
				System.out.println("글 추가 실패");
				response.getWriter().print("<script>alert('글 추가 실패');</script>");
			}
			doGet(request, response);
		}else if(type.equals("수정하기")) {
			if(userId.equals(request.getParameter("writerId"))) {
				PostVO post = boardDao.selectPostById(Integer.parseInt(request.getParameter("postId")));
				
				request.setAttribute("postInfo", post);
				getServletContext().getRequestDispatcher("/board/updatePost.jsp").forward(request, response);
				
			}else {
				// 권한 X
			}
			
		}
		
	} // end doPost

	
}
