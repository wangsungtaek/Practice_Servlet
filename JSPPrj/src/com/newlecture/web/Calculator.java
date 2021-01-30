package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator")
public class Calculator extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		Cookie[] inCookie = request.getCookies();
		String inExp = "0";
		if(inCookie != null) {
			for(Cookie c : inCookie)
				if(c.getName().equals("exp")) {
					inExp = c.getValue();
					break;
				}
		}
		PrintWriter out = response.getWriter();
		
		out.write("<!DOCTYPE html>");
		out.write("<html>");
		out.write("<head>");
		out.write("<meta charset=\"UTF-8\">");
		out.write("<title>Insert title here</title>");
		out.write("<style>");
		out.write("input {");
		out.write("	width: 50px;");
		out.write("	height: 50px;");
		out.write("}");
		out.write(".output {");
		out.write("	height: 50px;");
		out.write("	background: #e9e9e9;");
		out.write("	font-size: 24px;");
		out.write("	font-weight: bold;");
		out.write("	text-align: right;");
		out.write("	padding: 0 5px;");
		out.write("}");
		out.write("</style>");
		out.write("</head>");
		out.write("<body>");
		out.write("	<form method=\"post\">");
		out.write("		<table>");
		out.printf("			<tr><td class=\"output\" colspan=\"4\">%s</td></tr>",inExp);
		out.write("			<tr>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"CE\"></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"C\"></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"BS\"></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"/\"></td>");
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"7\"></td>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"8\"></td>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"9\"></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"*\"></td>");
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"4\"></td>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"5\"></td>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"6\"></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"-\"></td>");
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"1\"></td>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"2\"></td>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"3\"></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"+\"></td>");
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td></td>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"0\"></td>");
		out.write("				<td><input type=\"submit\" name=\"dot\" value=\".\"></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"=\"></td>");
		out.write("			</tr>");
		out.write("		</table>");
		out.write("	</form> ");
		out.write("</body>");
		out.write("</html>");
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
//			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
//			try {
//				express = String.valueOf(engine.eval(express));
//			} catch (ScriptException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			System.out.print("=");
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
		
		expressCookie.setPath("/calculator");
		response.addCookie(expressCookie);
		response.sendRedirect("calculator");	
	}
}
