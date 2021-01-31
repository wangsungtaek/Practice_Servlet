package com.newlecture.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/spag")
public class Spag extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int num = 0;
		String num_ = request.getParameter("n");
		if(num_ != null && !num_.equals(""))
			num = Integer.parseInt(num_);
		
		String result;
		
		if(num%2 != 0)
			result = "Ȧ��";
		else
			result = "¦��";
		request.setAttribute("result", result);
		
		ArrayList list = new ArrayList();
		list.add("aaa");
		list.add("bbb");
		request.setAttribute("list",list);
		
		Map notice = new HashMap();
		notice.put("a", 111);
		notice.put("b", 222);
		request.setAttribute("notice", notice);
		
		
		RequestDispatcher dispatcher
			= request.getRequestDispatcher("spag.jsp");
		dispatcher.forward(request, response);
	}
}
