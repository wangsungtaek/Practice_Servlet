package com.newlecture.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.newlecture.web.entity.Notice;

public class NoticeTestService {

	public ArrayList<Notice> getList() {
		return getList("title","",1);
	}
	public ArrayList<Notice> getList(int page) {
		return getList("title","",page);
	}
	public ArrayList<Notice> getList(String field, String query, int page) {
		ArrayList<Notice> list = new ArrayList<Notice>();
		
		String sql = "SELECT * FROM ("
				+ "	SELECT rownum num, n.* FROM ("
				+ "		SELECT * FROM NOTICE1 WHERE "+field+" LIKE ? ORDER BY regdate DESC"
				+ ")n )"
				+ "WHERE num BETWEEN ? AND ?";
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "scott", "tiger");
			PreparedStatement prst = con.prepareStatement(sql);
			prst.setString(1, "%"+query+"%");
			prst.setInt(2, 1+(page-1)*10);
			prst.setInt(3, page*10);
			ResultSet rs = prst.executeQuery();
			while(rs.next()) {
				Notice n = new Notice(rs.getInt("id"),
									  rs.getString("title"),
									  rs.getDate("regdate"),
									  rs.getString("writer_id"),
									  rs.getInt("hit"),
									  rs.getString("files"),
									  rs.getString("content"));
				list.add(n);
			}
			
			rs.close();
			prst.close();
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
	public static void main(String[] args) {
//		NoticeTestService service = new NoticeTestService();
//		ArrayList<Notice> nlist = service.getList();
//		for(Notice n : nlist) {
//			System.out.println(n.getTitle()+","+n.getRegdate());
//		}
	}
}
