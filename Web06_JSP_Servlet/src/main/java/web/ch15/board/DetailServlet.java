package web.ch15.board;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 게시글 데이터를 detail.jsp 페이지로 송신
@WebServlet("/detatil")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DetailServlet() {
    }

    // URL 요청은 doGet() 메소드를 호출
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// detail.jsp로 전송할 데이터를 BoardBean 객체에 저장
		BoardBean board = new BoardBean();
		
		board.setNo(1);
		board.setTitle("테스트 게시글");
		board.setContent("테스트 내용");
		board.setWriter("TEST");
		board.setRegDate(new Date());
		
		// request attribute 방식으로 객체 데이터 전송
		request.setAttribute("board", board);
		
		// ServletContext : 애플리케이션 정보 제공
		ServletContext context = getServletContext();
		// ServletContext 객체 참조
		
		
		// RequestDispatcher : 클라이언트로부터 발생된 요청을 
		// 					다른 경로의 리소스(Servlet or JSP)로 전송하는 역할
		RequestDispatcher dispatcher = context.getRequestDispatcher("/ch15/board/detail.jsp");
		dispatcher.forward(request, response);
		// request와 response를 포워딩(전송?)
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
