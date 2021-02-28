package com.newlecture.web.controller.admin.notice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(
		fileSizeThreshold = 1024 * 1024,
		maxFileSize = 1024 * 1024 * 50,
		maxRequestSize = 1024 * 1024 * 50 * 5
)

@WebServlet("/admin/board/notice/regTest")
public class RegTestController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/regTest.jsp").
		forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 파일 읽어오기
		Part filePart = request.getPart("file");
		// 2. Part타입을 Stream 타입으로 변경
		InputStream fis = filePart.getInputStream();
		
		// 3. 경로에 쓰일 파일명 읽기
		String fileName = filePart.getSubmittedFileName();
		// 4. 경로에 쓰일 절대 경로 읽어오기
		String realPath = request.getServletContext().getRealPath("/upload");
		// 5. 저장경로 만들기 (절대경로 + 구분자 + 파일명)
		String filePath = realPath + File.pathSeparator + fileName;
		// 6. 경로확인
		System.out.println(filePath);
		
		// 7. output을 위한 fileoutstream선언
		FileOutputStream fos = new FileOutputStream(filePath);
		
		// 8. 파일 읽기(바이트단위로)
		byte[] buffer = new byte[1024]; //1kb
		int size = 0;
		while((size = fis.read(buffer)) != -1 ) {
			fos.write(buffer, 0, size);
			System.out.println(size);
		}
			
		fos.close();
		fis.close();
		
		response.sendRedirect("regTest");
	}
}
