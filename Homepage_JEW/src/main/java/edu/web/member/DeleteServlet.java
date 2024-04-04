package edu.web.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete.do")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MemberDAO dao;
	
    public DeleteServlet() {
    	System.out.println("DeleteServlet 생성자");
    	dao = MemberDAOImple.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reqId = (String)request.getSession().getAttribute("userId");
		int res = dao.delete(reqId);
		String path = "", msg = "";
		if(res == 1) {
			request.getSession().invalidate();
			msg = "회원 탈퇴 성공";
			path = "/login.jsp";
		}else {
			msg = "회원 탈퇴 실패";
			path = "/memberResult.jsp";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("path", path);
		getServletContext().getRequestDispatcher("/alert.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
