package Project;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ProjectDAO {
	private Connection conn;

	public ProjectDAO() {
		// TODO Auto-generated constructor stub
	}

	public ProjectDAO(Connection conn) {// 建構子
		this.conn = conn;
	}

	// 新增計畫
	public boolean insertProject(ProjectBean projectBean) {
		try {
			String sqlString = "insert into AllProject(pName,pTarget,pSDate,pDescribe) values(?,?,?,?)";//要小心新增欄位的順序

			PreparedStatement preState = conn.prepareStatement(sqlString);
			preState.setString(1, projectBean.getpName());
			preState.setInt(2, projectBean.getpTarget());
			preState.setString(3, projectBean.getpSDate());
			preState.setString(4, projectBean.getpDescribe());
			int rs = preState.executeUpdate();

//	      Statement stmt = conn.createStatement();
//	      System.out.println(sqlString);
			// int updatecount = stmt.executeUpdate(sqlString);
			preState.close();
			if (rs >= 1)
				return true;
			else
				return false;
		} catch (Exception e) {
			System.err.println("提交計畫時發生錯誤:" + e);
			return false;
		}
	}

	// 刪除計畫
	public int deleteProject(ProjectBean projectBean) {
		try {
			// delete from Project where pName=' '
			String sqlString = "delete from AllProject where pID=?";

			PreparedStatement preState = conn.prepareStatement(sqlString);
			preState.setString(1, projectBean.getpID());
			int rs = preState.executeUpdate();

//		      Statement stmt = conn.createStatement();
//		      System.out.println(sqlString);
			// int updatecount = stmt.executeUpdate(sqlString);
			preState.close();
			return rs;
		} catch (Exception e) {
			System.err.println("刪除計畫時發生錯誤:" + e);
			return 0;
		}
	}
	
	//修改計畫內容
	public int updateProject(ProjectBean projectBean) {
		try {
			// delete from Project where pName=' '
			String sqlString = "UPDATE AllProject SET pName=?,pTarget=?,pDescribe=?,pSDate=? WHERE pID=?";

			PreparedStatement preState = conn.prepareStatement(sqlString);
			preState.setString(1, projectBean.getpName());
			preState.setInt(2, projectBean.getpTarget());
			preState.setString(3, projectBean.getpDescribe());
			preState.setString(4, projectBean.getpSDate());
			preState.setString(5, projectBean.getpID());
			int rs = preState.executeUpdate();

//		      Statement stmt = conn.createStatement();
//		      System.out.println(sqlString);
			// int updatecount = stmt.executeUpdate(sqlString);
			preState.close();
			return rs;
		} catch (Exception e) {
			System.err.println("刪除計畫時發生錯誤:" + e);
			return 0;
		}
	}
	

	// 查詢單一計畫
	public ProjectBean selectProject(ProjectBean projectBean) {

		String sqlString = " select * from AllProject where pID=?";

		ProjectBean p = new ProjectBean();// 另外建立ProjectBean的物件，存查詢回來的值
		try {
			PreparedStatement preState = conn.prepareStatement(sqlString);
			preState.setString(1,projectBean.getpID());//第一的?為pID的值
			ResultSet rs = preState.executeQuery();// 回傳查詢所有資料

			while(rs.next()) {
				p.setpID(rs.getString("pID"));
				p.setpName(rs.getString("pName"));
				p.setpDescribe(rs.getString("pDescribe"));
				p.setpTarget(rs.getInt("pTarget"));
				p.setpSDate(rs.getString("pSDate").substring(0,16));//因要取得格式為yyyy-MM-ddThh:mm
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return p;

	}

	// 查詢所有筆數資料
	public List<ProjectBean> selectAll() {
		List<ProjectBean> list = new ArrayList<ProjectBean>();
		String sqlString = " select * from AllProject";
		try {
			PreparedStatement preState = conn.prepareStatement(sqlString);
			ResultSet rs = preState.executeQuery();// 回傳查詢所有資料

			while (rs.next()) {
				ProjectBean p = new ProjectBean();// 建立ProjectBean的物件
				p.setpID(rs.getString("pID"));
				p.setpName(rs.getString("pName"));
				p.setpDescribe(rs.getString("pDescribe"));
				p.setpTarget(rs.getInt("pTarget"));
				
				list.add(p);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
