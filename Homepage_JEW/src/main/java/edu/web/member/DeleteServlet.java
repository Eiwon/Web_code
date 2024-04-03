package edu.web.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// TODO : memberResult.jsp에서 이동
// 		 로그인된 사용자 아이디를 가져와서 DB에 회원 정보 삭제
//		 삭제 성공 후에 login.jsp 페이지로 이동

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
			response.sendRedirect("login.jsp");
		}else {
			msg = "회원 탈퇴 실패";
			path = "/memberResult.jsp";
			response.sendRedirect("memberResult.jsp");
		}
		request.setAttribute("msg", msg);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
