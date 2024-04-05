package edu.web.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register.do")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public RegisterServlet() {
    	System.out.println("RegisterServlet() 생성자");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("RegisterServlet doPost()");
		int res = MemberDAOImple.getInstance()
				.insert(new MemberVO(
						request.getParameter("userId"), request.getParameter("password"), 
						request.getParameter("email"), request.getParameter("emailAgree"), 
						request.getParameterValues("interest"), request.getParameter("phone"), 
						request.getParameter("introduce")));
		String path = "", msg = "";
		if(res == 1) {
			path = "/login.jsp";
			msg = "회원 가입 성공";
		}else {
			path = "/memberRegister.jsp";
			msg = "회원 가입 실패";
		}
		// PrintWriter out = response.getWriter();
		// out.print("<script>alert('등록성공');</script>");
		// out.print("<script>location.href='login.jsp';</script>");
		// sendRedirect로는 response 전달이 안되서 사용 불가
		
		request.setAttribute("path", path);
		request.setAttribute("msg", msg);
		getServletContext().getRequestDispatcher("/alert.jsp").forward(request, response);
		
	} // end doPost

}
