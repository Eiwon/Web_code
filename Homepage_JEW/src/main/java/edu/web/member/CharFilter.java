package edu.web.member;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CharFilter extends HttpFilter implements Filter{
	private static final long serialVersionUID = 1L;

	public CharFilter() {
		System.out.println("CharFilter 생성자 호출");
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("CharFilter init()");
	}
	
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("CharFilter doFilter()");
		req.setCharacterEncoding("UTF-8");
		chain.doFilter(req, res);
	} // end doFileter
	
	@Override
	public void destroy() {
		System.out.println("CharFilter destroy()");
	}

}
