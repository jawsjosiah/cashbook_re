package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import vo.Member;

@WebServlet("/SelectMemberOneController")
public class SelectMemberOneController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// CashBookListByMonth.jsp 페이지에서 링크타고 왔음 
		
		// 현재 연결한 클라이언트(브라우저)에 대한 세션값을 받아옴. 
		HttpSession session = request.getSession();
		
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		System.out.println(sessionMemberId + " <-- sessionMemberId ( SelectMemberOneController.doGet() )");
		
		// 로그아웃 상태라면 
		if(sessionMemberId == null) {
			response.sendRedirect(request.getContextPath()+"/LogoutController");
			return;
		}
		
		Member member = new Member();
		
		MemberDao memberDao = new MemberDao();
		
		member = memberDao.selectMemberOne(sessionMemberId);
		
		// sessionMemberId 따로 넘김 
		// member 필드 보내는 것으로 수정 
		request.setAttribute("memberId", member.getMemberId());
		request.setAttribute("memberPw", member.getMemberPw());
		request.setAttribute("createDate", member.getCreateDate());
		
		request.getRequestDispatcher("/WEB-INF/view/memberOne.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
