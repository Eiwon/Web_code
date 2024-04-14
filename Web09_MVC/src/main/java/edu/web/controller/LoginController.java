package edu.web.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.web.domain.MemberVO;
import edu.web.persistence.MemberDAO;
import edu.web.persistence.MemberDAOImple;

public class LoginController {

	private static final String LOGIN_URL = "WEB-INF/login/";
	private static final String LOGIN = "login";
	private static final String REGISTER = "register";
	private static final String MAIN = "index";
	private static final String EXTENSION = ".jsp";
    
	private static LoginController instance = null;
	private MemberDAO memberDao = null;
	
	private LoginController() {
		memberDao = MemberDAOImple.getInstance();
	};
	
	public static LoginController getInstance() {
		if(instance == null)
			instance = new LoginController();
		return instance;
	}
	
	// /login/login.jsp 로 이동
	public void loginGET(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException{
		String path = "/" + LOGIN_URL + LOGIN + EXTENSION;
		context.getRequestDispatcher(path).forward(request, response);
	} // end loginGET
	
	// 유효한 id, pw이면 세션 생성 후 ?로 이동, 아니면 /login/login.jsp로 이동
	public void loginPOST(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException{
		System.out.println("loginPOST()");
		String memberId = request.getParameter("memberId");
		String pw = request.getParameter("pw");
		String successPath = context.getContextPath() + "/" + MAIN + EXTENSION;
		
		System.out.println(request.getAttribute("returnURI"));
		
		
		String resId = memberDao.loginChk(memberId, pw);
		if(resId != null) {
			System.out.println("인증 성공");
			request.getSession().setAttribute("memberId", memberId);
			request.getSession().setMaxInactiveInterval(600);
			response.sendRedirect(successPath);
		}else {
			System.out.println("잘못된 ID 또는 비밀번호");
			loginGET(request, response, context);
		}
		
	} // end loginPOST
	
	// /login/register.jsp 로 이동
	public void registerGET(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException{	
		String path = "/" + LOGIN_URL + REGISTER + EXTENSION;
		context.getRequestDispatcher(path).forward(request, response);
	} // end registerGET
	
	// 회원정보를 등록 후 login.jsp 로 이동
	public void registerPOST(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws ServletException, IOException{	
		int res = memberDao.insertMember(new MemberVO(
				request.getParameter("memberId"), 
				request.getParameter("pw"), 
				request.getParameter("name"), 
				request.getParameter("email"), 
				request.getParameter("phone")));
		
		if(res == 1) {
			System.out.println("멤버 등록 성공");
			loginGET(request, response, context);
		}else {
			System.out.println("멤버 등록 실패");
			loginGET(request, response, context);
		}
	} // end registerPOST
	
	// 비동기 : 아이디 중복 체크
	public void idDupChk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String memberId = request.getParameter("memberId");
		String resultId = memberDao.idDupChk(memberId);
		
		if(resultId == null) {
			System.out.println("사용 가능한 ID");
			response.getWriter().write("1");
		}else {
			System.out.println("사용할 수 없는 ID");
			response.getWriter().write("0");
		}
	} // end idDupChk
}
