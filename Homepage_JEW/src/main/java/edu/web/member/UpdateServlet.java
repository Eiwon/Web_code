package edu.web.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/update.do")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MemberDAO dao;
    public UpdateServlet() {
    	System.out.println("UpdateServlet 생성자");
    	dao = MemberDAOImple.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UpdateServlet doPost()");
		String userId = (String)request.getSession().getAttribute("userId");
		MemberVO member = new MemberVO(userId, request.getParameter("password"), 
				request.getParameter("email"), request.getParameter("emailAgree"), 
				request.getParameterValues("interest"), request.getParameter("phone"), 
				request.getParameter("introduce"));
		
		String msg = "";
		System.out.println(member.toString());
		
		int res = dao.update(member);
		if(res == 1) {
			msg = "회원 정보 수정 성공";
		}else {
			msg = "회원 정보 수정 실패";
			member = dao.selectByUserId(userId);
		}
		request.setAttribute("userInfo", member);
		request.setAttribute("msg", msg);
		getServletContext().getRequestDispatcher("/memberResult.jsp").forward(request, response);
		
	} // end doPost

}
