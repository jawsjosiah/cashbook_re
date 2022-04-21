package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vo.Cashbook;

public class CashbookDao {
	public int deleteCashBook(int cashbookNo) {
		// 삭제한 행 
		int row = 0;
		
		// DB 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null; 
		
		String sql = "DELETE FROM cashbook WHERE cashbook_no=?";
		
		String dburl = "jdbc:mariadb://localhost:3307/cashbook"; // DB 주소
		String dbuser = "root"; // DB 아이디
		String dbpw = "java1234"; // DB 패스워드
		try { 
			Class.forName("org.mariadb.jdbc.Driver");
			
			conn = DriverManager.getConnection(dburl, dbuser, dbpw);
			conn.setAutoCommit(false); // 자동 커밋을 해제 
			
			PreparedStatement stmt3 = null;
			String sql3 = "SET FOREIGN_KEY_CHECKS=0";
			stmt3 = conn.prepareStatement(sql3);
			
			// hashtag를 삭제하는 코드 
			PreparedStatement stmt2 = null; 
			
			String sql2 = "DELETE FROM hashtag WHERE cashbook_no=?";
			stmt2 = conn.prepareStatement(sql2);
			stmt2.setInt(1, cashbookNo);
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cashbookNo);
			
			row = stmt.executeUpdate(); 
			System.out.println(row + " <-- row(삭제한 행의 수) CashbookDao.deleteCashBook");
			
			PreparedStatement stmt4 = null;
			String sql4 = "SET FOREIGN_KEY_CHECKS=1";
			stmt4 = conn.prepareStatement(sql4);
			
			
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				// 메모리 누수 방지 DB 자원 반납 
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return row;
		
	}
	
	public Cashbook selectCashBookOne(int cashbookNo) {
		// 
		Cashbook cashbook = null; 
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		
		String sql = "SELECT"
				+ "		cashbook_no cashbookNo"
				+ "		, cash_date cashDate"
				+ "		, kind"
				+ "		, cash"
				+ "		, memo"
				+ "		, update_date updateDate"
				+ "		, create_date createDate"
				+ "	FROM cashbook"
				+ "	WHERE cashbook_no = ?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/cashbook","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cashbookNo);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				cashbook = new Cashbook();
				cashbook.setCashbookNo(rs.getInt("cashbookNo"));
				cashbook.setCashDate(rs.getString("cashDate"));
				cashbook.setKind(rs.getString("kind"));
				cashbook.setCash(rs.getInt("cash"));
				cashbook.setMemo(rs.getString("memo"));
				cashbook.setUpdateDate(rs.getString("updateDate"));
				cashbook.setCreateDate(rs.getString("createDate"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return cashbook;
	}
	
	
	
	public void insertCashbook(Cashbook cashbook, List<String> hashtag) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/cashbook","root","java1234");
			conn.setAutoCommit(false); // 자동커밋을 해제
			
			String sql = "INSERT INTO cashbook(cash_date,kind,cash,memo,update_date,create_date)"
					+ " VALUES(?,?,?,?,NOW(),NOW())";
			
			// insert + select 방금생성된 행의 키값 ex) select 방금입력한 cashbook_no from cashbook;
			stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); 
			stmt.setString(1, cashbook.getCashDate());
			stmt.setString(2, cashbook.getKind());
			stmt.setInt(3, cashbook.getCash());
			stmt.setString(4, cashbook.getMemo());
			stmt.executeUpdate(); // insert
			rs = stmt.getGeneratedKeys(); // select 방금입력한 cashbook_no from cashbook
			int cashbookNo = 0;
			if(rs.next()) {
				cashbookNo = rs.getInt(1);
			}
			
			// hashtag를 저장하는 코드
			PreparedStatement stmt2 = null;
			for(String h : hashtag) {
				String sql2 = "INSERT INTO hashtag(cashbook_no, tag, create_date) VALUES(?, ?, NOW())";
				stmt2 = conn.prepareStatement(sql2);
				stmt2.setInt(1, cashbookNo);
				stmt2.setString(2, h);
				stmt2.executeUpdate();
			}
			
			conn.commit();
		} catch(Exception e) {
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
	}

	
	public List<Map<String, Object>> selectCashbookListByMonth(int y, int m) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		/*
		 SELECT 
		 	cashbook_no cashbookNo
		 	,DAY(cash_date) day
		 	,kind
		 	,cash
		 	,LEFT(memo, 5) memo
		 FROM cashbook
		 WHERE YEAR(cash_date) = ? AND MONTH(cash_date) = ?
		 ORDER BY DAY(cash_date) ASC, kind ASC
		 */
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT"
				+ "		 	cashbook_no cashbookNo"
				+ "		 	,DAY(cash_date) day"
				+ "		 	,kind"
				+ "		 	,cash"
				+ "			,LEFT(memo, 5) memo"
				+ "		 FROM cashbook"
				+ "		 WHERE YEAR(cash_date) = ? AND MONTH(cash_date) = ?"
				+ "		 ORDER BY DAY(cash_date) ASC, kind ASC";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/cashbook","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, y);
			stmt.setInt(2, m);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cashbookNo", rs.getInt("cashbookNo"));
				map.put("day", rs.getInt("day"));
				map.put("kind", rs.getString("kind"));
				map.put("cash", rs.getInt("cash"));
				map.put("memo", rs.getString("memo"));
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
