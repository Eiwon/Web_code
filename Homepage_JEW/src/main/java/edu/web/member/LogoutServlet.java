package edu.web.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// TODO : 로그인된 사용자만 접근 가능.
// userId 세션을 제거하고, login.jsp로 이동

@WebServlet("/logout.do")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public LogoutServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String userId = (String)request.getSession().getAttribute("userId");
		String msg = "";
		System.out.println("LogoutServlet doGet() : userId = " + userId);
		if(userId == null) { // 로그인 안하고 접근시
			msg = "로그인 하세요.";
		}else { // 로그인 상태에서 접근시
			request.getSession().invalidate();
			msg = "로그 아웃 성공";
		}
		request.setAttribute("msg", msg);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
