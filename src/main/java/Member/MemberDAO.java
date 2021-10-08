package Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class MemberDAO {
	private Connection conn;
	
	//建構子
	public MemberDAO(Connection conn) {
		this.conn = conn;
	}
	
	//新增會員 OK
	public boolean addMember(MemberBean m) throws SQLException {
		//ID是識別欄位，不用加
		String sqlStr="insert into Member(mName, mAccount, mPassword, mStatus)"
							+ "values(?,?,?,?)";
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		preState.setString(1, m.getName());
		preState.setString(2, m.getAccount());
		preState.setString(3, m.getPassword());
		preState.setString(4, m.getStatus());
		int updateCount = preState.executeUpdate();
		preState.close();
		if (updateCount>=1) {
			return true;
		}else {
			return false;
		}
	}
	//修改密碼 OK
	public boolean changePwdByIdAndName(MemberBean m) throws SQLException {
		String sqlStr="update Member set mPassword=? where mId=? and mName=?";
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		preState.setString(1, m.getPassword());
		preState.setInt(2, m.getId());
		preState.setString(3, m.getName());
		int updateCount = preState.executeUpdate();
		if (updateCount>=1) {
			preState.close();
			return true;
		}else {
			preState.close();
			return false;
		}
	}
	//修改會員資格 OK
	public boolean updateStatusByIdAndName(MemberBean m) throws SQLException {
		String sqlStr="update Member set mStatus=? where mId=? and mName=?";
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		preState.setString(1, m.getStatus());
		preState.setInt(2, m.getId());
		preState.setString(3, m.getName());
//		System.out.println("debug.dao");
		int updateCount = preState.executeUpdate();
		preState.close();
		if (updateCount>=1) {
			return true;
		}else {
			return false;
		}
	}
	//查詢全部會員  ok
	public MemberBean queryMemberAll() throws SQLException {
		ArrayList<Integer> id = new ArrayList<Integer>();
		ArrayList<String> name = new ArrayList<String>();
		ArrayList<String> act = new ArrayList<String>();
		ArrayList<String> pwd = new ArrayList<String>();
		ArrayList<String> status = new ArrayList<String>();
		
		String sqlStr="select * from Member";
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		ResultSet rs = preState.executeQuery();
		while(rs.next()) {
			id.add(Integer.valueOf(rs.getInt(1)));
			name.add(rs.getString(2));
			act.add(rs.getString(3));
			pwd.add(rs.getString(4));
			status.add(rs.getString(5));
		}
		
		MemberBean allMemberData = new MemberBean(id,name,act,pwd,status);
		preState.close();
		return allMemberData;
	}
	//用ID查詢會員 
	public MemberBean queryMemberById(MemberBean m) throws SQLException {
		String sqlStr="Select * from Member where mId=?";
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		preState.setInt(1, m.getId());
		ResultSet rs = preState.executeQuery();
		MemberBean q = new MemberBean();
		if (rs.next()) {
			q.setId(rs.getInt(1));
			q.setName(rs.getString(2));
			q.setAccount(rs.getString(3));
			q.setPassword(rs.getString(4));
			q.setStatus(rs.getString(5));
		}
		return q;
	}
	//用ID刪除會員
	public boolean deleteMemberById(MemberBean m) throws SQLException {
		String sqlStr="delete from Member where mId=?";
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		preState.setInt(1, m.getId());
		int count = preState.executeUpdate();
		preState.close();
		if (count>=1) {
			return true;
		}else {
			return false;
		}
	}
	public MemberBean login(MemberBean login_Member_Data) throws SQLException {
		
		String sqlStr="select * from Member where mAccount=? and mPassword=?";
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		preState.setString(1, login_Member_Data.getAccount());
		preState.setString(2, login_Member_Data.getPassword());
		ResultSet rs = preState.executeQuery();
		boolean check = rs.next();
//		String[] loginArray = new String[8];
		MemberBean memberData = new MemberBean();
		if (check) {
			memberData.setId(rs.getInt(1));
			memberData.setName(rs.getString(2));
			memberData.setStatus(rs.getString(3));
			memberData.setAccount(rs.getString(4));
			memberData.setPassword(rs.getString(5));
			//姓名放入index of 0
//			loginArray[0]=rs.getString(2);
			//身分放入index of 1
//			if(rs.getString(3).equals("管理員")) {
//				loginArray[1]="管理員";
//			}else {
//				loginArray[1]="一般會員";
//			}
			//密碼放入index of 2
//			loginArray[2]=rs.getString(5);
			//id放入index of 3
//			loginArray[3]=Integer.toString(rs.getInt(1));
		

		}else {
			System.out.println("登入失敗");
			memberData.setStatus("登入失敗");
//			loginArray[0]="登入失敗";
		}
		return memberData;
	}
}
