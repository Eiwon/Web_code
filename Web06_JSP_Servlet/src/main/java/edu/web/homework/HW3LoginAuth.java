package edu.web.homework;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HW3LoginAuth
 */
@WebServlet("/homework/HW3_login_auth")
public class HW3LoginAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HashSet<String> sessionMap;
	
    public HW3LoginAuth() {
    	System.out.println("HW3LoginAuth 생성자");
    	sessionMap = new HashSet<>();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Post from /homework/HW3");
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher;
		String returnPath = "";
		if(request.getParameter("login") != null) {
			System.out.println("request login");
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
		
			System.out.println("id : " + id + ", pw : " + pw);
			System.out.println("request session : " + request.getRequestedSessionId());
			if(id.equals("test") && pw.equals("1234")){ // 로그인 성공
				request.setAttribute("id", id);
				request.setAttribute("isValid", "valid");
				sessionMap.add(request.getRequestedSessionId());
				returnPath = "/homework/HW3_login_result.jsp";
			}else{ // 로그인 실패
				request.setAttribute("isValid", "invalid");
				returnPath = "/homework/HW3.jsp";
			}
		
		}else if(request.getParameter("logout") != null) {
			System.out.println("request logout");
			if(sessionMap.contains(request.getRequestedSessionId())) {
				System.out.println("session id 일치");
				sessionMap.remove(request.getRequestedSessionId());
			}
			returnPath = "/homework/HW3.jsp";
		}else if(!sessionMap.contains(request.getRequestedSessionId())) {
			System.out.println("세션 만료");
			returnPath = "/homework/HW3.jsp";
		}
		
		
		dispatcher = context.getRequestDispatcher("/homework/HW3_login_auth.jsp");
		dispatcher.forward(request, response);
	}

}
