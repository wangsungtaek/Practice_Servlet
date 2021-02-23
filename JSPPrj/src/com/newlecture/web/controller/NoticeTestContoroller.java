package com.newlecture.web.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeTestService;

@WebServlet("/noticeTest")
public class NoticeTestContoroller extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String field = "title";
		String query = "";
		int page = 1;
		
		String field_ = request.getParameter("f");
		String query_ = request.getParameter("r");
		String page_ = request.getParameter("p");
		
		if(field_ != null && !field_.equals("")) field = field_;
		if(query_ != null && !query_.equals("")) query = query_;
		if(page_ != null && !page_.equals("")) page = Integer.parseInt(page_);
		
		NoticeTestService service = new NoticeTestService();
		
		ArrayList<Notice> list = service.getList(field,query,page);
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("noticeTest.jsp").forward(request, response);
	}
}
