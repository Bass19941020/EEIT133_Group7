package Sponsorship;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PaymentDAO {
	private Connection conn;

	public PaymentDAO(Connection conn) {
		this.conn = conn;
	}

	// 新增訂單
	public boolean addOrder(PaymentBean p) throws SQLException {
		String sqlStr = "insert into SponsorshipFee(sID,sName,sPID,sPName,sAmount)" + "values(?,?,?,?,?)";
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		preState.setInt(1, p.getOrderNumber());
		preState.setString(2, p.getSponsorName());
		preState.setInt(3, p.getProjectID());
		preState.setString(4, p.getProjectName());
		preState.setInt(5, p.getAmount());
		preState.execute();
		preState.close();
		return true;
	}

	// 刪除訂單內某個贊助專案
	public void deleteProject(PaymentBean p) throws SQLException {
		String sqlStr = "delete SponsorshipFee where sID=? where sPID=? ";
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		preState.setInt(1, p.getOrderNumber());
		preState.setInt(2, p.getProjectID());
		preState.execute();
		preState.close();
	}

	// 刪除整筆訂單
	public boolean deleteOrder(int deleteID) throws SQLException {
		String sqlStr = "delete SponsorshipFee where sID=?";
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		preState.setInt(1, deleteID);
		preState.execute();
		preState.close();
		return false;
	}

	// 更改訂單內的贊助專案
	public void modifyOrderByProject(PaymentBean p) throws SQLException {
		String sqlStr = "update SponsorshipFee set sPName=? ,sPID=? where sID=? ";
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		preState.setString(1, p.getProjectName());
		preState.setInt(2, p.getProjectID());
		preState.execute();
		preState.close();
	}

	// 更改訂單
	public void updateOrder(String updateColumn, Object updateValue, int orderNumber) throws SQLException {
		String sqlStr = "update SponsorshipFee set " + updateColumn + "=?" + " where sID=?";
		PreparedStatement preState = conn.prepareStatement(sqlStr);

		if (updateValue instanceof String) {
			preState.setString(1, (String) updateValue);
		} else {
			preState.setInt(1, (int) updateValue);
		}
		preState.setInt(2, orderNumber);
		preState.execute();
		System.out.println(sqlStr);
		preState.close();
	}

	// 查詢訂單
	public PaymentBean searchOrder(String columnName, Object searchValue) throws SQLException {
		String sqlStr = "select * from SponsorshipFee where " + columnName + "=?";
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		if (searchValue instanceof String) {
			preState.setString(1, (String) searchValue);
		} else {
			preState.setInt(1, (int) searchValue);
		}
		ResultSet rs = preState.executeQuery();
		PaymentBean searchBean = new PaymentBean();
//		boolean isNext = true;
		while (rs.next()) {
//			System.out.println(isNext);
			int trackingNo = rs.getInt(1);
			String sponsor = rs.getString(2);
			int projectNo = rs.getInt(3);
			String projectName = rs.getString(4);
			int amount = rs.getInt(5);
			searchBean = new PaymentBean(trackingNo, sponsor, projectNo, projectName, amount);
//			System.out.println("OrderNumber:" + trackingNo + "Sponsor:" + sponsor + "ProjectNumber:" + projectNo
//					+ "ProjectName:" + projectName + "Amount:" + amount);
//			isNext=rs.next();
		}
		rs.close();
		preState.close();
		return searchBean;

	}

	public ArrayList<PaymentBean> searchOrder1(String columnName, Object searchValue) throws SQLException {
		ArrayList<PaymentBean> list = new ArrayList<PaymentBean>();
		String sqlStr = "select * from SponsorshipFee where " + columnName + "=?";
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		if (searchValue instanceof String) {
			preState.setString(1, (String) searchValue);
		} else {
			preState.setInt(1, (int) searchValue);
		}
		ResultSet rs = preState.executeQuery();

		while (rs.next()) {

			PaymentBean searchBean = new PaymentBean();
			searchBean.setOrderNumber(rs.getInt(1));
			searchBean.setSponsorName(rs.getString(2));
			searchBean.setProjectID(rs.getInt(3));
			searchBean.setProjectName(rs.getString(4));
			searchBean.setAmount(rs.getInt(5));
//			int trackingNo = rs.getInt(1);
//			String sponsor = rs.getString(2);
//			int projectNo = rs.getInt(3);
//			String projectName = rs.getString(4);
//			int amount = rs.getInt(5);
//			searchBean = new PaymentBean(trackingNo, sponsor, projectNo, projectName, amount);
			list.add(searchBean);
//			System.out.println(rs.getString(2));
		}
		rs.close();
		preState.close();
		return list;

	}

	public ArrayList<PaymentBean> searchAll() throws SQLException {
		ArrayList<PaymentBean> list = new ArrayList<PaymentBean>();
		String sqlStr = "select * from SponsorshipFee";
		PreparedStatement preState = conn.prepareStatement(sqlStr);

		ResultSet rs = preState.executeQuery();

		while (rs.next()) {

			PaymentBean searchBean = new PaymentBean();
			searchBean.setOrderNumber(rs.getInt(1));
			searchBean.setSponsorName(rs.getString(2));
			searchBean.setProjectID(rs.getInt(3));
			searchBean.setProjectName(rs.getString(4));
			searchBean.setAmount(rs.getInt(5));
			list.add(searchBean);

		}
		rs.close();
		preState.close();
		return list;

	}
}
