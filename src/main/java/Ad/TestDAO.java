// DAO: Database Access Object
// 專責與Dept Table之新增,修改,刪除與查詢
package Ad;
import java.sql.*;

public class TestDAO {
	
	private Connection conn;
	
	//建構子
	public TestDAO(Connection conn) {
		this.conn = conn;
	}
	
	// 新增資料
	public boolean createTest(Test testData) {
		try {
			String sqlString = "insert into AdPost values('"
					+ testData.getAid()+"','"
					+ testData.getAname()+"','"
					+ testData.getApost()+"')";
			
			Statement stmt = conn.createStatement();
			System.out.println(sqlString);
			int updatecount = stmt.executeUpdate(sqlString);
			stmt.close();
			if (updatecount >= 1) return true;
			else                  return false;
		} catch (Exception e) {
			System.err.println("新增資料時發生錯誤:" + e);
			return false;
		}
	}
	
	
	// 刪除資料
	public boolean deleteTest(int aId) {
		try {
			String sqlString = "DELETE FROM AdPost " +
					"WHERE aId = " + aId;
			Statement stmt = conn.createStatement();
			int deletecount = stmt.executeUpdate(sqlString);
			stmt.close();
			if (deletecount >= 1) return true;
			else              return false;
		} catch (Exception e) {
			System.err.println("刪除資料時發生錯誤: "+ e);
			return false;
		}
		
	}
	
	
	// 修改資料
	public boolean updateTest(Test testData) {
		try {
			String sqlString = "UPDATE AdPost " +
					"SET aName = '" + testData.getAname() +"' "+ 
					"WHERE aId = " + testData.getAid();
			
			Statement stmt = conn.createStatement();
			int updatecount = stmt.executeUpdate(sqlString);
			stmt.close();
			if (updatecount >= 1) return true;
			else                  return false;
		} catch (Exception e) {
			System.err.println("更新部門資料時發生錯誤:" + e);
			return false;
		}
	}
	
	// 查詢資料
	public Test findTest(int aId) {
		try {
			Test tes = null;
			String aName;
			String aPost; //新增加的
			
			Statement stmt = conn.createStatement();
			String sqlString = "SELECT * " +
					"FROM AdPost WHERE aId = " + aId;
			
			ResultSet rs = stmt.executeQuery(sqlString);
			
			if (rs.next()) {
				aName  =  rs.getString(2);
				aPost  =  rs.getString(3);
				tes = new Test(aId, aName, aPost);
			}
			rs.close();
			stmt.close();
			return tes;
			
		} catch (Exception e) {
			System.err.println("尋找部門資料時發生錯誤:" + e);
			return null;
		}
	

   
//    -------------
//    String sqlStr = "select * from member where id = ?";
//	PreparedStatement preState = conn.prepareStatement(sqlStr);
//
//	preState.setInt(1, aId);
//	ResultSet rs = preState.executeQuery();
//
//	Test tes = null;
//
//	if (rs.next()) {
//		tes = new Test(aId,aName,aPost);
//		tes.setAid(rs.getInt("aId")); // 資料庫欄位名稱
//		tes.setAname(rs.getString("aName"));
//		tes.setApost(rs.getString("aPost"));
//	}
//
//	rs.close();
//	preState.close();
//	return tes;
//    ----------------
    
	}

}