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

@WebServlet("/deleteMemberController")
public class DeleteMemberController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// memberOne.jsp 페이지에서 링크타고 왔음 
		// 현재 연결한 클라이언트(브라우저)에 대한 세션값을 받아옴.
		HttpSession session = request.getSession();
		
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		System.out.println(sessionMemberId + " <-- sessionMemberId ( DeleteMemberPwController.doGet() )");
		
		// 로그아웃 상태라면 
		if(sessionMemberId == null) {
			
			response.sendRedirect(request.getContextPath()+"/logoutController");
			
			return;
		}
		
		request.setAttribute("sessionMemberId", sessionMemberId);
		
		request.getRequestDispatcher("/WEB-INF/view/deleteMember.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 현재 연결한 클라이언트(브라우저)에 대한 세션값을 받아옴. 
		HttpSession session = request.getSession();
		
		// session에 저장되어 있는 sessionMemberId 값을 받아서 문자열 변수에 저장한다. 
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		System.out.println(sessionMemberId + " <-- sessionMemberId ( DeleteMemberController.doPost() )");
		
		// 로그아웃 상태라면 
		if(sessionMemberId == null) {
			
			response.sendRedirect(request.getContextPath()+"/logoutController");
			
			return;
		}
		
		// 회원 수정 페이지 updateMemberPw.jsp 페이지에서 넘겨받은 비밀번호 저장 , 계속 받고있는 sessionId도 저장 
		String memberId = sessionMemberId;
		String memberPw = request.getParameter("memberPw");
		
		System.out.println(memberId + " <-- memberId // DeleteMemberController.doPost()");
		System.out.println(memberPw + " <-- memberPw // DeleteMemberController.doPost()");
		
		// 새로운 Member 객체 생성 
		Member member = new Member();
		
		// member 필드에 받아온 값 저장 
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		// 모델 호출 
		MemberDao memberDao = new MemberDao();
		
		int row = memberDao.deleteMember(member);
		System.out.println(row + " <-- row ( DeleteMemberPwController.doPost() )"); 
		
		response.sendRedirect(request.getContextPath()+"/logoutController");
	}

}
