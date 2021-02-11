package com.newlecture.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;

@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String sql = "SELECT * FROM NOTICE1 WHERE ID=?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"scott", "tiger");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			rs.next();
			
			String title = rs.getString("title");
			Date regdate = rs.getDate("regdate");
			String writer_id = rs.getString("writer_id");
			int hit = rs.getInt("hit");
			String files = rs.getString("files");
			String content = rs.getString("content");
			
			Notice notice = new Notice(
					id,
					title,
					regdate,
					writer_id,
					hit,
					files,
					content);
			
			request.setAttribute("n",notice);
			
//			request.setAttribute("title", title);
//			request.setAttribute("regdate", regdate);
//			request.setAttribute("writer_id", writer_id);
//			request.setAttribute("hit", hit);
//			request.setAttribute("files", files);
//			request.setAttribute("content", content);
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/view/notice/detail.jsp").forward(request, response);
	}
}
