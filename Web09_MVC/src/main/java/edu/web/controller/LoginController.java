package edu.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController {

	private static LoginController instance = null;
	
	private LoginController() {};
	
	public static LoginController getInstance() {
		if(instance == null)
			instance = new LoginController();
		return instance;
	}
	
	// /login/login.jsp
	public void loginGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
	}
}
