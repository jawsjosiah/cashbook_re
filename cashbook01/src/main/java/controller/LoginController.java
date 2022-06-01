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

@WebServlet("/loginController")
public class LoginController extends HttpServlet {
	
	// 로그인 폼 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 현재 연결한 클라이언트(브라우저)에 대한 세션값을 받아옴. 
		HttpSession session = request.getSession();
		
		// MemberId 받는 부분부터 if문 끝나는 부분까지 사용이 되는지 ? 
		
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		System.out.println(sessionMemberId + " <-- sessionMemberId ( LoginController.doGet() )");
		
		if(sessionMemberId != null) {
			// 이미 로그인이 되어있는 상태
			// 가계부 달력으로 이동 
			response.sendRedirect(request.getContextPath()+"/cashBookListByMonthController");
			// 메서드 종료 
			return;
		}
		
		// 로그인 되어있는(?) -> 안되어있는(수업 이후 개인적으로 주석 수정) 멤버라면 리다이렉트
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}

	// 로그인 액션 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 되어있는 멤버라면 리다이렉트
		// Login.jsp에서 멤버 아이디와 비밀번호를 받아서 저장한다. 
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		// 새로운 Member 객체 생성 
		Member member = new Member(); 
		
		// member 필드에 받아온 값 저장 
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		// 모델 호출 
		MemberDao memberDao = new MemberDao();
		
		// 같은 의미 String sessionMemberId = memberDao.selectMemberByIdPw(member);
		// 참조 객체 member의 필드를 메서드 매개변수로 일일이 넣지 않아도 된다. 
		String returnMemberId = memberDao.selectMemberByIdPw(member); 
		System.out.println(returnMemberId + " <-- returnMemberId ( LoginController.doPost() )");
		
		if(returnMemberId == null ) {
			// 로그인 실패시 로그인 폼을 재요청 
			
			System.out.println("로그인 실패 <-- LoginController.doPost() ");
			
			// sendRedirect는 당연히 get방식 
			response.sendRedirect(request.getContextPath()+"/loginController");
			
			// else문 대신 사용한 return 
			return;
		} 
		
		// 로그인 성공 
		// 현재 연결한 클라이언트(브라우저)에 대한 세션값을 받아옴. 
		HttpSession session = request.getSession(); 
		
		session.setAttribute("sessionMemberId", returnMemberId);
		
		
		response.sendRedirect(request.getContextPath()+"/cashBookListByMonthController");
	}

}
