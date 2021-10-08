package Ad;
import java.sql.*;

public class AdDAO {
	
	private Connection conn;
	
	//建構子
	public AdDAO(Connection conn) {
		this.conn = conn;
	}
	
	// 新增資料
	public boolean createAd(Ad AdData) {
		try {
			String sqlString = "insert into AdPost values('"
					+ AdData.getAid()+"','"
					+ AdData.getAname()+"','"
					+ AdData.getApost()+"')";
			
			Statement stmt = conn.createStatement();
			System.out.println(sqlString);
			int updatecount = stmt.executeUpdate(sqlString);
			stmt.close();
			if (updatecount >= 1)
				return true;
			else
				return false;
		} catch (Exception e) {
			System.err.println("新增資料時發生錯誤:" + e);
			return false;
		}
	}
	
	
	// 刪除資料
	public boolean deleteAd(int aId) {
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
	public boolean updateAd(Ad AdData) {
		try {
			String sqlString = "UPDATE AdPost " +
					"SET aName = '" + AdData.getAname() +"' "+ 
					"WHERE aId = " + AdData.getAid();
			
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
	public Ad findAd(int aId) {
		try {
			Ad ad = null;
			String aName;
			String aPost; //新增加的
			
			Statement stmt = conn.createStatement();
			String sqlString = "SELECT * " +
					"FROM AdPost WHERE aId = " + aId;
			
			ResultSet rs = stmt.executeQuery(sqlString);
			
			if (rs.next()) {
				aName  =  rs.getString(2);
				aPost  =  rs.getString(3);
				ad = new Ad(aId, aName, aPost);
			}
			rs.close();
			stmt.close();
			return ad;
			
		} catch (Exception e) {
			System.err.println("尋找部門資料時發生錯誤:" + e);
			return null;
		}
   
	}

}