package com.newlecture.web;

import java.io.IOException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/calc3")
public class Calc3 extends HttpServlet {
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] inCookies = request.getCookies();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String express = "";
		
		// post 읽기
		String inValue = request.getParameter("value");
		String inOper = request.getParameter("operator");
		String inDot = request.getParameter("dot");
		
		// exp 쿠키 찾기
		if(inCookies != null) {
			for(Cookie c : inCookies)
				if(c.getName().equals("exp")){
					express = c.getValue();
					break;
				}
		}

		if(inOper != null && inOper.equals("=")) {
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
			try {
				express = String.valueOf(engine.eval(express));
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(inOper != null && inOper.equals("C")) {
			express = "";
		}
		else {
			express += (inValue == null)? "":inValue;
			express += (inOper == null)? "":inOper;
			express += (inDot == null)? "":inDot;
		}
		
		Cookie expressCookie = new Cookie("exp", express);
		if(inOper != null && inOper.equals("C"))
			expressCookie.setMaxAge(0);
		response.addCookie(expressCookie);
		response.sendRedirect("calcpage");
	}
}
