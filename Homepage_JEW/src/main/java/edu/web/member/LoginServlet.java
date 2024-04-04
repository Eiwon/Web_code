package edu.web.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginAuth.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private int maxInterval = 60;
	private MemberDAO dao = null;
    
    public LoginServlet() {
    	System.out.println("LoginServlet 생성자");
    	dao = MemberDAOImple.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("msg", "잘못된 접근입니다!!!!!!!!");
		
		if(request.getSession().getAttribute("userId") == null) {
			request.setAttribute("path", "/login.jsp");
		}else {
			request.setAttribute("path", "/loginResult.jsp");
		}
		getServletContext().getRequestDispatcher("/alert.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginServlet doPost()");
		
		String reqId = request.getParameter("userId");
		String reqPw = request.getParameter("password");	
		String matchingPw = dao.selectPwById(reqId);
		String path = "", msg = "";
		
		System.out.println("reqId : " + reqId + ", reqPw : " + reqPw);
		
		if((matchingPw != null) && (reqPw.equals(matchingPw))) {
			// 로그인 성공
			request.getSession().setAttribute("userId", reqId);
			request.getSession().setMaxInactiveInterval(maxInterval);
			msg = "로그인 성공";
			path = "/loginResult.jsp";
		}else {// 로그인 실패
			msg = "잘못된 아이디 또는 비밀번호입니다.";
			path = "/login.jsp";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("path", path);
		getServletContext().getRequestDispatcher("/alert.jsp").forward(request, response);
		
	} // end doPost

}
