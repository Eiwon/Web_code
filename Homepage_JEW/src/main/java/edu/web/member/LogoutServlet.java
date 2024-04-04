package edu.web.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		request.setAttribute("path", "/login.jsp");
		getServletContext().getRequestDispatcher("/alert.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
