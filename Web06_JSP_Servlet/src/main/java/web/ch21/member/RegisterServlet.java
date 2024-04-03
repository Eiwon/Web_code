package web.ch21.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register.do")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    MemberDAO dao;   
	
    public RegisterServlet() {
    	System.out.println("RegisterServlet 생성자");
    	dao = MemberDAOImple.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// sendRedirect() : 특정 경로로 이동
		// request는 소멸되기 때문에 데이터 전송 불가.
		// dispatcher.forward() : request와 함께 특정 경로로 이동
		response.sendRedirect("/Web06_JSP_Servlet/ch21/register.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("RegisterServlet doPost()");
		MemberVO member = new MemberVO(request.getParameter("userId"), request.getParameter("password"), 
				request.getParameter("email"), request.getParameter("emailAgree"), 
				request.getParameterValues("interest"), request.getParameter("phone"), request.getParameter("introduce"));
		int res = dao.insert(member);
		System.out.println(res);
	} // end doPost

}
