package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logoutController")
public class LogoutController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// CashBookListByMonth.jsp의 요청받고 해당 페이지로 옴 
		
		// session 갱신 메서드 : 기존 세션을 지우고 새로운 세션 공간을 부여 
		request.getSession().invalidate(); 
		
		/*
		HttpSession session = request.getSession();
		session.invalidate();
		*/
		
		response.sendRedirect(request.getContextPath()+"/loginController");
	}

}
