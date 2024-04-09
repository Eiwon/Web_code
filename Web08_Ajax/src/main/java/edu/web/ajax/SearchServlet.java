package edu.web.ajax;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import oracle.jdbc.OracleDriver;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search.do")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() " + request.getParameter("value"));
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String target = request.getParameter("value");
		JSONArray jsonList = new JSONArray();
		
		List<SearchVO> list = DBConnector.getInstance().selectByTitle(target);
		
		for(SearchVO vo : list) {
			JSONObject obj = new JSONObject();
			obj.put("sno", vo.getSno());
			obj.put("title", vo.getTitle());
			System.out.println("JSON conversion : " + vo.toString());
			jsonList.add(obj);
		}
		
		response.getWriter().append(jsonList.toString());
		
	} // end doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
