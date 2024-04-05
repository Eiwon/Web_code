package edu.web.member;

import java.io.*;
import java.util.HashSet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PathFilter extends HttpFilter implements Filter{
	private static final long serialVersionUID = 1L;
	
	private static HashSet<String> validPath = null;
	private static HashSet<String> sessionPath = null;
	private static String[] validList = {
			"/login.jsp", "/loginAuth.do", "/memberRegister.jsp", "/register.do", "/logout.do", 
			"/select.do", "/update.do", "/alert.jsp", "/delete.do", "/loginResult.jsp",
			"/memberResult.jsp", "/memberUpdate.jsp", "/board/list", "/boardMain.jsp", 
			"/board/readPost.jsp", "/board/writePost.jsp"
			};
	
	@Override
	public void init() throws ServletException {
		validPath = new HashSet<>();
		sessionPath = new HashSet<>();
		for(int i = 0; i < validList.length; i++) {
			validPath.add(validList[i]);
			if(i > 4)
				sessionPath.add(validList[i]);
		}
	}
	
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("PathFilter doFilter");
		boolean isError = false;
		String msg = "", path = "/login.jsp";
		String userId = (String)req.getSession().getAttribute("userId");
		String reqPath = req.getServletPath();
		
		System.out.println(reqPath);
		
		if(!validPath.contains(reqPath)) { // 존재하지 않는 경로로 접근시
			System.out.println("잘못된 접근");
			msg = "잘못된 경로입니다.";
			if(userId != null) {
				path = "/loginResult.jsp";
			} // 세션이 있다면 loginResult.jsp로 이동, 없다면 login.jsp로 이동
			isError = true;
		}else if(sessionPath.contains(reqPath)) {
			if(!req.isRequestedSessionIdValid()) { // 세션 만료시
				System.out.println("세션 만료");
			msg = "세션이 만료되었습니다.";
			isError = true;
			}else if(userId == null) {
				// 세션 없이 로그인, 회원가입을 제외한 페이지 접근시
			System.out.println("세션 없이 접근");
			msg = "로그인 후 이용해주세요.";
			isError = true;
			}
		}
		
		if(isError) {
			req.setAttribute("msg", msg);
			req.setAttribute("path", path);
			getServletContext().getRequestDispatcher("/alert.jsp").forward(req, res);
			return;
		}
		
		chain.doFilter(req, res);
	}
	
	@Override
	public void destroy() {
	}
}
