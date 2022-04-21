package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HashtagDao;

@WebServlet("/SelectTagByTagController")
public class SelectTagByTagController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글 결과도 받기 위해서 미리 인코딩 설정 
		request.setCharacterEncoding("utf-8");
		
		// TagList.jsp 페이지에서 넘겨받은 값 받는다. 
		String tag = request.getParameter("tag");
		System.out.println(tag + " <-- tag (SelectTagByTagController.java) ");
		
		HashtagDao hashtagDao = new HashtagDao(); 
		
		List<Map<String,Object>> list = hashtagDao.selectTagListByTag(tag);
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("WEB-INF/view/tagListByTag.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
