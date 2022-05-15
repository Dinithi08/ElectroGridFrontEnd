package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@WebServlet("/StaffsAPI")
public class StaffsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Staff staffObj = new Staff();
          
    public StaffsAPI() {
        super();     
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String output = staffObj.insertStaff(request.getParameter("customerName"),
				request.getParameter("customerID"),
				request.getParameter("staffName"),
			    request.getParameter("staffName"),
			
				 request.getParameter("assignWork"),
				request.getParameter("mobile"));
		
				response.getWriter().write(output);		
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		 String output = staffObj.updateStaff(paras.get("hidStaffIDSave").toString(),
		paras.get("customerName").toString(),
		paras.get("customerID").toString(),
		paras.get("staffName").toString(),

		paras.get("assignWork").toString(),
		paras.get("mobile").toString());
		response.getWriter().write(output);
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		 String output = staffObj.deleteStaff(paras.get("staffID").toString());
		response.getWriter().write(output); 
	}
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request)
	{
	 Map<String, String> map = new HashMap<String, String>();
	try
	 {
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	 String queryString = scanner.hasNext() ?
	 scanner.useDelimiter("\\A").next() : "";
	 scanner.close();
	 String[] params = queryString.split("&");
	 for (String param : params)
	 { 
		 String[] p = param.split("=");
		 map.put(p[0], p[1]);
		 }
		 }
		catch (Exception e)
		 {
		 }
		return map;
		}

	
}
