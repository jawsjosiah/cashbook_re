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

@WebServlet("/insertMemberController")
public class InsertMemberController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Login.jsp 페이지에서 로그아웃이 된 상태로 링크타고 왔음 
		// 로그 아웃이 되었는지 먼저 검증 
		
		// 현재 연결한 클라이언트(브라우저)에 대한 세션값을 받아옴. 
		HttpSession session = request.getSession();
		
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		System.out.println(sessionMemberId + " <-- sessionMemberId ( InsertMemberController.doGet() )");
		
		if(sessionMemberId != null) {
			// 이미 로그인이 되어있는 상태 
			// 가계부 달력으로 이동 
			response.sendRedirect(request.getContextPath()+"/cashBookListByMonthController");
			// 메서드 종료 
			return;
		}
		
		// 로그아웃 상태가 맞으면 회원 가입 페이지로 이동 
		request.getRequestDispatcher("/WEB-INF/view/insertMember.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 현재 연결한 클라이언트(브라우저)에 대한 세션값을 받아옴. 
		HttpSession session = request.getSession();
		
		// session에 저장되어 있는 sessionMemberId 값을 받아서 문자열 변수에 저장한다. 
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		System.out.println(sessionMemberId + " <-- sessionMemberId ( InsertMemberController.doPost() )");
		
		// 만약 로그인 되어있다면 가계부 페이지로 보낸다 
		if(sessionMemberId != null) {
			response.sendRedirect(request.getContextPath()+"/cashBookListByMonthController");
			return; 
		}
		
		// 회원 가입 페이지 insertMember.jsp 페이지에서 넘겨받은 아이디와 비밀번호 저장 
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		System.out.println(memberId + " <-- memberId // InsertMemberController.doPost()");
		System.out.println(memberPw + " <-- memberPw // InsertMemberController.doPost()");
		
		// 새로운 Member 객체 생성 
		Member member = new Member();
		
		// member 필드에 받아온 값 저장 
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		// 모델 호출 
		MemberDao memberDao = new MemberDao();
		// 
		memberDao.insertMember(member);
				
		response.sendRedirect(request.getContextPath()+"/loginController");
		
		
	}

}
