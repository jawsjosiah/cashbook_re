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

@WebServlet("/SelectTagByTermController")
public class SelectTagByTermController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request.setCharacterEncoding("utf-8");
		
		// TagList.jsp에서 시작일과 종료일을 문자열로 받는다. 
		String beginDate = request.getParameter("beginDate");
		System.out.println(beginDate + " <-- beginDate (SelectTagByTermController) ");
		
		String endDate = request.getParameter("endDate");
		System.out.println(endDate + " <-- endDate (SelectTagByTermController) ");
		
		HashtagDao hashtagDao = new HashtagDao(); 
		
		List<Map<String,Object>> list = hashtagDao.selectTagRankListByTerm(beginDate,endDate);
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("WEB-INF/view/tagListByTerm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
