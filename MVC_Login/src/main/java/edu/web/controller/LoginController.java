package edu.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String LOGIN_URL = "WEB-INF/login/";
	
	private static final String LOGIN = "login";
	private static final String REGISTER = "register";
	
	private static final String EXTENSION = ".do";
	// GET					POST
	//login.do				login.do
	//register.do			register.do
	
	
    public LoginController() {
    }

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURL = request.getRequestURI();
		String requestMethod = request.getMethod();
		System.out.println("login service()");
		if(requestURL.contains(LOGIN + EXTENSION)) {
			if(requestMethod.equals("GET")) {
				String returnPath = request.getParameter("returnPath");
				
			}else if(requestMethod.equals("POST")) {
				if(request.getParameter("memberId").equals("test")) {
					
				}
			}
		}
		
		
	} // end service

}
