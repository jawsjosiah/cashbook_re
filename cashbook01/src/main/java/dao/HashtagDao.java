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

public class HashtagDao {
	public List<Map<String,Object>> selectTagRankList() {
	      List<Map<String,Object>> list = new ArrayList<>();
	      Connection conn = null;
	      PreparedStatement stmt = null;
	      ResultSet rs = null;
	      try {
	         /*
	             SELECT t.tag, t.cnt, RANK() over(ORDER BY t.cnt DESC) ranking
	            FROM 
	            (SELECT tag, COUNT(*) cnt
	            FROM hashtag
	            GROUP BY tag) t
	          */
	         Class.forName("org.mariadb.jdbc.Driver");
	         conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","mariadb1234");
	         String sql = "SELECT t.tag, t.cnt, RANK() over(ORDER BY t.cnt DESC) rank"
	               + "            FROM"
	               + "            (SELECT tag, COUNT(*) cnt"
	               + "            FROM hashtag"
	               + "            GROUP BY tag) t";
	         stmt = conn.prepareStatement(sql);
	         rs = stmt.executeQuery();
	         while(rs.next()) {
	            Map<String, Object> map = new HashMap<>();
	            map.put("tag", rs.getString("tag"));
	            map.put("cnt", rs.getInt("t.cnt"));
	            map.put("rank", rs.getInt("rank"));
	            list.add(map);
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
	      return list;
	   }
	
	public List<Map<String,Object>> selectTagRankListByKind(String kind) {
		List<Map<String,Object>> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		
		try {
			/*
			 	SELECT kind, t.tag, t.cnt, RANK() over(ORDER BY t.cnt DESC) "ranking"
				FROM 
				(SELECT c.kind kind, tag, COUNT(*) cnt 
				FROM hashtag t INNER JOIN cashbook c 
				ON t.cashbook_no = c.cashbook_no
				WHERE c.kind = '수입'
				GROUP BY t.tag) t;
			 
			 */
			// 동적 쿼리? 
			Class.forName("org.mariadb.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","mariadb1234");
			String sql = "SELECT kind, t.tag, t.cnt, RANK() over(ORDER BY t.cnt DESC) \"ranking\""
					+ " FROM "
					+ " (SELECT c.kind kind, tag, COUNT(*) cnt "
					+ " FROM hashtag t INNER JOIN cashbook c "
					+ " ON t.cashbook_no = c.cashbook_no"
					+ " WHERE c.kind = ?" // ? 이 부분에 홑따옴표 넣으면 동작 안함. 상수값이면 써도 되는데 
					+ " GROUP BY t.tag) t";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, kind);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String,Object> map = new HashMap<>();
				map.put("kind", rs.getString("kind"));
				map.put("tag", rs.getString("t.tag"));
				map.put("cnt", rs.getInt("t.cnt"));
				map.put("ranking", rs.getInt("ranking"));
				list.add(map);
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
		
		return list;
	}
	
	public List<Map<String,Object>> selectTagRankListByTerm(String beginDate, String endDate) {
		List<Map<String,Object>> list = new ArrayList<>(); 
		
		Connection conn = null;
		PreparedStatement stmt = null; 
		ResultSet rs = null;
		
		String sql = "SELECT kind, t.tag, t.cnt, RANK() over(ORDER BY t.cnt DESC) \"ranking\", t.cashDate"
				+ " FROM "
				+ " (SELECT c.kind kind, tag, COUNT(*) cnt, c.cash_date cashDate"
				+ " FROM hashtag t INNER JOIN cashbook c "
				+ " ON t.cashbook_no = c.cashbook_no "
				+ " WHERE c.cash_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d')  AND STR_TO_DATE(?,'%Y-%m-%d')"
				+ " GROUP BY t.tag) t";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","mariadb1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, beginDate);
			stmt.setString(2, endDate);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String,Object> map = new HashMap<>();
				map.put("kind", rs.getString("kind"));
				map.put("tag", rs.getString("t.tag"));
				map.put("cnt", rs.getString("t.cnt"));
				map.put("ranking", rs.getString("ranking"));
				map.put("cashDate", rs.getString("t.cashDate"));
				list.add(map);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		return list; 
	}
	
	 /*
	    SELECT t.tag tag, c.cash_date cashDate, c.kind kind, c.cash cash, c.memo memo
		FROM hashtag t INNER JOIN cashbook c 
		ON t.cashbook_no = c.cashbook_no 
		WHERE tag = '커피' -- t.tag = ? / tag = ? 
		ORDER BY cashDate DESC;
	 */
	public List<Map<String, Object>> selectTagListByTag(String tag) {
		List<Map<String,Object>> list = new ArrayList<>(); 
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		
		String sql = "SELECT t.tag tag, c.cash_date cashDate, c.kind kind, c.cash cash, c.memo memo"
				+ "		FROM hashtag t INNER JOIN cashbook c"
				+ "		ON t.cashbook_no = c.cashbook_no"
				+ "		WHERE tag = ?"
				+ "		ORDER BY cashDate DESC";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","mariadb1234");
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tag);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String,Object> map = new HashMap<>();
				map.put("tag", rs.getString("tag"));
				map.put("cashDate", rs.getString("cashDate"));
				map.put("kind", rs.getString("kind"));
				map.put("cash", rs.getString("cash"));
				map.put("memo", rs.getString("memo"));
				list.add(map);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return list;
		
	}
}
