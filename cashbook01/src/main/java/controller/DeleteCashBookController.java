package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CashbookDao;

@WebServlet("/DeleteCashBookController")
public class DeleteCashBookController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// CashBookOne.jsp에서 넘겨받은 값 저장 
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		
		CashbookDao cashbookDao = new CashbookDao();
		
		// deleteCashbook 호출 
		int row =  cashbookDao.deleteCashBook(cashbookNo);
		System.out.println(row + " <-- row (DeleteCashBookController.java)");
		response.sendRedirect(request.getContextPath()+"/CashBookListByMonthController");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
