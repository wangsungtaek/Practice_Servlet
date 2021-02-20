package com.newlecture.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.newlecture.web.entity.Notice;

public class NoticeService {
	
	// List Read
	public List<Notice> getNoticeList(){
		
		return getNoticeList("title", "", 1);
	}
	
	public List<Notice> getNoticeList(int page){
		
		return getNoticeList("title", "", page);
	}
	
	public List<Notice> getNoticeList(String field, String query, int page){
		
		List<Notice> list = new ArrayList<>();
		
		String sql = "SELECT * FROM ("
				+ "	SELECT ROWNUM NUM, n.*"
				+ "	FROM (SELECT * FROM NOTICE1 WHERE "+field+" LIKE ? ORDER BY REGDATE DESC) n"
				+ ") "
				+ "WHERE NUM BETWEEN ? AND ?";
		
		// 1, 11, 21, 31 - > an = 1+(page-1)*10
		// 10, 20, 30, 40 -> page * 10;
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"scott", "tiger");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%"+query+"%");
			st.setInt(2, 1+(page-1)*10);
			st.setInt(3, page*10);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()){
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String writer_id = rs.getString("writer_id");
				Date regdate = rs.getDate("regdate");
				int hit = rs.getInt("hit");
				String files = rs.getString("files");
				String content = rs.getString("content");
				list.add(new Notice(id,title,regdate,writer_id,hit,files,content));
			}
			
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
		return list;
	}

	// List Count
	public int getNoticeCount() {
		
		return getNoticeCount("title", "");
	}
	
	public int getNoticeCount(String field, String query) {
		int count = 0;
		String sql = "SELECT COUNT(ID) count FROM ("
				+ "	SELECT ROWNUM NUM, n.*"
				+ "	FROM (SELECT * FROM NOTICE1 WHERE"+field+" LIKE ? ORDER BY REGDATE DESC) n"
				+ ") ";
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"scott", "tiger");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%"+query+"%");
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) count = rs.getInt("count");
			
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
		return count;
	}
 
	// Read Notice
	public Notice getNotice(int id) {
		Notice notice = null;
		String sql = "SELECT * FROM NOTICE1 WHERE ID=?";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"scott", "tiger");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next()){
				int nid = rs.getInt("id");
				String title = rs.getString("title");
				String writer_id = rs.getString("writer_id");
				Date regdate = rs.getDate("regdate");
				int hit = rs.getInt("hit");
				String files = rs.getString("files");
				String content = rs.getString("content");
				notice = new Notice(id,title,regdate,writer_id,hit,files,content);
			}
			
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
		return notice;
	}
	// 다음 게시물
	public Notice getNextNotice(int id) {
		Notice notice = null;
		String sql = "SELECT * FROM NOTICE1"
				+ " WHERE id = ("
				+ "	SELECT ID FROM NOTICE1"
				+ "	WHERE REGDATE > (SELECT REGDATE FROM NOTICE1 WHERE id = ?)"
				+ "	AND rownum = 1"
				+ ")";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"scott", "tiger");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next()){
				int nid = rs.getInt("id");
				String title = rs.getString("title");
				String writer_id = rs.getString("writer_id");
				Date regdate = rs.getDate("regdate");
				int hit = rs.getInt("hit");
				String files = rs.getString("files");
				String content = rs.getString("content");
				notice = new Notice(id,title,regdate,writer_id,hit,files,content);
			}
			
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
		return notice;
	}
	// 이전 게시물
	public Notice getPrevNotice(int id) {
		Notice notice = null;
		String sql = "SELECT * FROM (SELECT * FROM NOTICE1 ORDER BY REGDATE desc)"
				+ "WHERE regdate < (SELECT regdate FROM notice1 WHERE id = ?)"
				+ "AND rownum = 1";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"scott", "tiger");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next()){
				int nid = rs.getInt("id");
				String title = rs.getString("title");
				String writer_id = rs.getString("writer_id");
				Date regdate = rs.getDate("regdate");
				int hit = rs.getInt("hit");
				String files = rs.getString("files");
				String content = rs.getString("content");
				notice = new Notice(id,title,regdate,writer_id,hit,files,content);
			}
			
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
		
		return notice;
	}
	// insert
	public void insertNotice(Notice n) {
		String sql = "INSERT INTO notice1 VALUES (?,?,?,?,to_date(?,'YYYY-MM-DD HH24:mi:ss'),?,?,?)";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,"scott", "tiger");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, n.getId());
			st.setString(2, n.getTitle());
			st.setString(3, n.getWriter_id());
			st.setString(4, n.getContent());
			st.setString(5, n.getRegdate_S());
			st.setInt(6, n.getHit());
			st.setString(7, n.getFiles());
			st.setInt(8, n.getPub());
			
			st.executeUpdate();
						
			st.close();
			con.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
//		NoticeService dao = new NoticeService();
//		int day = 2;
//		for(int i = 36; i<60; i++) {
//			dao.insertNotice(new Notice(i,
//										"JSP를 활용한..",
//										"2021-03-"+day+" 13:30:"+i,
//										"newlec",
//										0,
//										"none",
//										"게시판을 만들기 위한....",
//										0));
//			day++;
//		}
	}
}
