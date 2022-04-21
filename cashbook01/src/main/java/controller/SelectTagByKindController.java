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


@WebServlet("/SelectTagByKindController")
public class SelectTagByKindController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String kind = request.getParameter("kind");
		
		
		HashtagDao hashtagDao = new HashtagDao(); 
		
		List<Map<String,Object>> list = hashtagDao.selectTagRankListByKind(kind);
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("WEB-INF/view/tagListByKind.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
