package com.newlecture.web.controller.admin.notice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;

@MultipartConfig(
		fileSizeThreshold = 1024*1024,
		maxFileSize = 1024*1024*50,
		maxRequestSize = 1024*1024*50*5
)
@WebServlet("/admin/board/notice/reg")
public class RegController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/reg.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String isOpen = request.getParameter("open");
		
		Collection<Part> parts = request.getParts();
		
		// 아래는 한 바이트씩 읽어서 한 바이트씩 사용하고있다.
		// ( 예를들어, 가정에서 a양동이에서 b양동이로 물을 옭길때 티스푼으로 옮기는 것과
		// 비슷하다 ) 적어도 양동이가 필요하다.
//		int b;
//		if((b = fis.read()) != -1) {
//			fos.write(b);
//		}
		StringBuilder builder = new StringBuilder();
		for(Part p : parts) {
			if(!p.getName().equals("file")) continue;
			
			Part filePart = p;
			// 파일명을 읽어온다.
			String fileName = filePart.getSubmittedFileName();
			builder.append(fileName);
			builder.append(",");
			
			InputStream fis = filePart.getInputStream();
			// 절대경로를 얻는다
			String realPath = request.getServletContext().getRealPath("/upload");
			
			// 절대경로 + 구분자 + 파일이름
			// 운영체제별로 파일 경로 구분자가 다르기 때문에 자바에서 제공하는 
			// File.pathSeaparator를 사용한다.
			String filePath = realPath + "/" + fileName;
			System.out.println(filePath);
			FileOutputStream fos = new FileOutputStream(filePath);
			
			byte[] buf = new byte[1024]; // 1kb
			int size = 0;
			while((size = fis.read(buf)) != -1) {
				//fos.wirte(buf); 현재 설정된 1024의 버퍼를 전부 쓰고 있지만, 마지막 버퍼는 꼭 1kb가 아닐 수 있기 때문에
									// 아래와 같이 사이즈를 설정해 주어야한다.
				fos.write(buf,0,size); // 위의 fis.read(buf)는 현재 담긴 버퍼를 반환해준다. 이를 size에 담았으니,
										// 마지막번째의 사이즈를 알 수 있다.
			}
			fos.close();
			fis.close();
		}
		builder.delete(builder.length()-1, builder.length());
		
		
			Boolean pub = false;
			if(isOpen != null)
				pub = true;
			
			Notice notice = new Notice();
			notice.setTitle(title);
			notice.setContent(content);
			notice.setPub(pub);
			notice.setWriterId("newlec");
			notice.setFiles(builder.toString());
			NoticeService service = new NoticeService();
			service.insertNotice(notice);
			
			response.sendRedirect("list");
		
	}
}
