package edu.web.member;

import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter extends HttpFilter implements Filter{
	@Override
	public void init() throws ServletException {
	}
	
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("LoginFilter doFilter");
		chain.doFilter(req, res);
		String reqPath = req.getServletPath();
		if(!reqPath.equals("/login.jsp") && req.getSession().getAttribute("userId") == null) {
			// 로그인 페이지가 아니고 userId 세션이 없는 상태라면,
			System.out.println("<script>alert('로그인 후 이용해주세요.');</script>");
			req.setAttribute("msg", "로그인 후 이용해주세요.");
			res.sendRedirect("login.jsp");
		}
	}
	
	@Override
	public void destroy() {
	}
}
