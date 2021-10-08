// DAO: Database Access Object
// �M�d�PDept Table���s�W,�ק�,�R���P�d��
package Activity;
import java.sql.*;

public class ActivityDAO {

  private Connection conn;

  //建構子
  public ActivityDAO(Connection conn) {
		this.conn = conn;
  }

 
  //報名活動
  public boolean insertActivitySignUp(ActivityBean studentData) {
    try {
      String sqlString = "insert into ActivitySignUp values('"
	                  	   	+studentData.getName()+"','"
		                    +studentData.getId()+"','"
                            + studentData.getBirthyear()+"','"
                            + studentData.getBirthmonth()+"','"
                            + studentData.getBirthday()+"','" 
                            + studentData.getZipcode()+"','"
                            + studentData.getAddress()+"','"
                            + studentData.getPhone()+"','"
                            + studentData.getMailaddress()+ "')";
                           
      Statement stmt = conn.createStatement();
      System.out.println(sqlString);
	  int updatecount = stmt.executeUpdate(sqlString);
      stmt.close();
      if (updatecount >= 1) return true;
      else                  return false;
	  } catch (Exception e) {
	    System.err.println("新增活動報名資料時發生錯誤:" + e);
		return false;
    }
  }
  
  //刪除報名資料
  public boolean deleteActivitySignUp(ActivityBean studentData) {
	  try {
		  String sql="delete from ActivitySignUp where Id=?";
		  String id=studentData.getId();

   	     
  	      
    	    PreparedStatement ps = conn.prepareStatement(sql);
    	    ps.setString(1, id);
    	
    	   
	                           
	     
	     
		  int updatecount = ps.executeUpdate();
		  ps.close();
	      if (updatecount >= 1) return true;
	      else                  return false;
		  } catch (Exception e) {
		    System.err.println("刪除活動報名資料時發生錯誤:" + e);
			return false;
	    }
	   
}
	     
	

	
  
  //修改報名資料
  public boolean reviseSignUpInformation(ActivityBean studentData ) {
	  try {
	      String sqlStr = "Update  ActivitySignUp Set name=?,Zipcode=?,Address=?,Phone=?,Mailaddress=? where Id=? ";
	   
			PreparedStatement preState = conn.prepareStatement(sqlStr);
		preState.setString(1, studentData.getName());
		preState.setInt(2, Integer.valueOf(studentData.getZipcode()));
		preState.setString(3,studentData.getAddress());
		preState.setInt(4, Integer.valueOf(studentData.getPhone()));
		preState.setString(5, studentData.getMailaddress());
		preState.setString(6, studentData.getId());
		System.out.println(studentData.getName());
		System.out.println(studentData.getAddress());
		System.out.println(studentData.getId());
		 
 	    
 	    preState.execute();
 	   
	                           
	     
	     
		  int updatecount = preState.executeUpdate();
		  preState.close();
	      if (updatecount >= 1) return true;
	      else                  return false;
		  } catch (Exception e) {
		    System.err.println("修改活動報名資料時發生錯誤:" + e);
			return false;
	    }
  }
  
  
  
  
  
  


}