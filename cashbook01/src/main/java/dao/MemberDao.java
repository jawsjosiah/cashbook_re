package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import util.DBUtil;
import vo.Member;

public class MemberDao {
	// 회원 가입
	/*
	INSERT INTO member values(
		?
		, PASSWORD(?)
		, NOW()
	);
	 */
	public void insertMember(Member member) {
			
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "INSERT INTO member(member_id, member_pw, create_date) values(?, PASSWORD(?), NOW())";

		try {
			// Class.forName("org.mariadb.jdbc.Driver");
			// conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","mariadb1234");
			conn = DBUtil.getConnection(); 
			conn.setAutoCommit(false); // 자동커밋을 해제
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			// 해당하는 문장을 두번 적어서 에러가 났는지? stmt.executeQuery(); // insert 
			
			rs = stmt.executeQuery(); // 쿼리 저장
			
			if(rs.next()) { // 객체에 쿼리의 정보들 받아서 저장
				// 캡슐 
				member.setMemberId(rs.getString("member_id"));
				member.setMemberPw(rs.getString("member_pw"));
				member.setCreateDate(rs.getString("create_date"));
				
			}
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	// 회원 정보 
	public Member selectMemberOne(String sessionMemberId) {
		Member member = null; 
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		
		String sql = "SELECT"
				+ "		member_id memberId"
				+ "		, member_pw memberPw"
				+ "		, create_date createDate"
				+ "	FROM member"
				+ "	WHERE member_id = ?";
		
		try {
			// Class.forName("org.mariadb.jdbc.Driver");
			// conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","mariadb1234");
			conn = DBUtil.getConnection(); 
						
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sessionMemberId); 
			
			rs = stmt.executeQuery(); // 쿼리 저장
			
			if(rs.next()) { // 객체에 쿼리의 정보들 받아서 저장
				// 캡슐 
				member = new Member();
				member.setMemberId(rs.getString("memberId"));
				member.setMemberPw(rs.getString("memberPw"));
				member.setCreateDate(rs.getString("createDate"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return member;
		
	}
	
	// 회원 수정
	public int updateMemberPw(Member member) {
			
		Connection conn = null;
		PreparedStatement stmt = null;
		// ResultSet rs = null; 
		
		int row = 0; // 쿼리가 실행되어 변화가 있었는지 확인하기 위한 변수 
		
		String sql = "UPDATE member SET member_pw = PASSWORD(?) WHERE member_id = ? ";
		
		try {
			// Class.forName("org.mariadb.jdbc.Driver");
			// conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","mariadb1234");
			conn = DBUtil.getConnection(); 
			conn.setAutoCommit(false); // 자동 커밋을 해제 
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberPw());
			stmt.setString(2, member.getMemberId()); 
			
			row = stmt.executeUpdate(); // 쿼리를 통해 결과가 수정되면 정수값 반환된다 
			
			
			conn.commit();
		} catch (Exception e) { 
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return row;
		
	}
	
	// 회원 탈퇴 
	public int deleteMember(Member member) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		int row = 0;
		
		String sql = "DELETE FROM member WHERE member_id = ? AND member_pw = PASSWORD(?)";
		
		try {
			// Class.forName("org.mariadb.jdbc.Driver");
			// conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","mariadb1234");
			conn = DBUtil.getConnection(); 
			conn.setAutoCommit(false); // 자동커밋을 해제
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw()); 
			
			row = stmt.executeUpdate(); // 쿼리 저장
			
			
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return row;
	}
	
	// 로그인 
	public String selectMemberByIdPw(Member member) {
		String memberId = null; 
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT member_id memberId FROM member WHERE member_id=? AND member_pw=PASSWORD(?)";
		try {
			// Class.forName("org.mariadb.jdbc.Driver");
			// conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","mariadb1234");
			conn = DBUtil.getConnection(); 
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				memberId = rs.getString("memberId");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return memberId;
	}
}
