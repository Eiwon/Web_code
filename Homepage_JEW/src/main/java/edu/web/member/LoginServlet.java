package edu.web.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// TODO : login.jsp에서 입력받은 아이디, 패스워드를 DB의 데이터와 비교해서
// 데이터가 일치하면 - 로그인 세션 생성, 로그인 성공(loginResult.jsp)로 이동
// 아이디 값에 대한 세션 생성. 만료 시간 60초
// 일치하지 않으면 - login.jsp로 이동(실패 alert)

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
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginServlet doPost()");
		
		String reqId = request.getParameter("userId");
		String reqPw = request.getParameter("password");		
		MemberVO searched = dao.selectByUserId(reqId);
		String path = "";
		String msg = "";
		
		System.out.println("reqId : " + reqId + ", reqPw : " + reqPw);
		
		if((searched != null) && (reqId.equals(searched.getUserId()) && reqPw.equals(searched.getPassword()))) {
			// 로그인 성공
			request.getSession().setAttribute("userId", reqId);
			request.getSession().setMaxInactiveInterval(maxInterval);;
			path = "/loginResult.jsp";
			msg = "로그인 성공";
		}else {
			// 로그인 실패
			path = "/login.jsp";
			msg = "잘못된 아이디 또는 비밀번호입니다.";
		}
		request.setAttribute("msg", msg);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(path);
		dispatcher.forward(request, response);
		
	} // end doPost

}
