package Member;

import javax.servlet.*;
//import javax.servlet.http.*;
//import java.io.PrintWriter;
//import java.io.IOException;
import java.io.*;

import java.sql.*;

//import javax.rmi.*;
import javax.naming.*;
import javax.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//@WebServlet(asyncSupported = true, urlPatterns = {"/MemberController"})
@WebServlet("/MemberController")
@MultipartConfig
/**
 * Servlet implementation class RegisterServlet
 */
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";
	DataSource ds = null;
	InitialContext ctxt = null;
	Connection conn = null;
    MemberDAO memberDAO = null;
    int id;
    String name;
	String account;
	String password;
	String status;
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			ctxt = new InitialContext();
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/DB7");
			conn = ds.getConnection();
			memberDAO = new MemberDAO(conn);
			System.out.println("開啟sql-server連線成功");
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void destroy() {
		if (conn != null) {
			try {
				conn.close();
				System.out.println("關閉sql-server連線");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	protected void doPost(HttpServletRequest rq, HttpServletResponse resp) throws ServletException, IOException {
		rq.setCharacterEncoding(CHARSET_CODE);
		resp.setContentType(CONTENT_TYPE);
		// To prevent caching
		resp.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		resp.setHeader("Pragma", "no-cache"); // HTTP 1.0
		resp.setDateHeader("Expires", -1); // Prevents caching at the proxy server
		
		//任務選擇器
		try {
			if (rq.getParameter("register") != null) {
				register(rq, resp);
			}else if(rq.getParameter("registerAdmin") != null) {
				registerAdmin(rq, resp);
			} else if (rq.getParameter("login") != null) {
				login(rq, resp);
			} else if (rq.getParameter("queryAll") != null) {
				queryAll(rq, resp);
			} else if (rq.getParameter("querySingle") != null) {
				querySingle(rq, resp);
			} else if (rq.getParameter("changePwd") != null) {
				changePwd(rq, resp);
			} else if (rq.getParameter("changeStatus") != null) {
				changeStatus(rq, resp);
			} else if (rq.getParameter("delete") != null) {
				deleteById(rq, resp);
			} 
		
		} catch (ServletException e) {
			System.out.println("Servlet錯誤");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO錯誤");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQL錯誤");
			e.printStackTrace();
		}
	}

	private void registerAdmin(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		name = request.getParameter("name").trim();
		account = request.getParameter("account").trim();
		password = request.getParameter("password").trim();
		status= request.getParameter("status").trim();
		
		MemberBean memberData = new MemberBean(name, account, password, status);
		if (memberDAO.addMember(memberData)) {
			System.out.println("加入會員成功");
			request.getSession(true).setAttribute("status", "seccess");
		}else {
			System.out.println("加入會員失敗");
			request.getSession(true).setAttribute("status", "fail");
		}
		request.getRequestDispatcher("/Member/Admin.jsp").forward(request, response);
	}

	private void querySingle(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int Input_Id = Integer.parseInt(request.getParameter("id"));
		MemberBean query_Data = new MemberBean(Input_Id);
		MemberBean rs;
		if ((rs=memberDAO.queryMemberById(query_Data)) != null) {
			System.out.println("查詢成功");
			request.getSession(true).setAttribute("querySingleData", rs);
			request.getRequestDispatcher("/Member/AdminSingleQuery.jsp").forward(request, response);
			
		}else {
			System.out.println("查詢失敗");
			request.getSession(true).setAttribute("status", "fail");
			request.getRequestDispatcher("/Member/Admin.jsp").forward(request, response);
		}
	}

	private void deleteById(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int Input_Id = Integer.parseInt(request.getParameter("id"));
		MemberBean del_Data = new MemberBean(Input_Id);
		
		if (memberDAO.deleteMemberById(del_Data)) {
			System.out.println("刪除成功");
			request.getSession(true).setAttribute("status", "seccess");
		}else {
			System.out.println("刪除失敗");
			request.getSession(true).setAttribute("status", "fail");
		}
		request.getRequestDispatcher("/Member/Admin.jsp").forward(request, response);
		
	}

	private void changeStatus(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int Input_Id = Integer.parseInt(request.getParameter("id"));
		String Input_Name = request.getParameter("name");
		String Input_Status = request.getParameter("status");
		MemberBean chgStatus_Data = new MemberBean(Input_Id, Input_Name, Input_Status);
		if (memberDAO.updateStatusByIdAndName(chgStatus_Data)) {
			System.out.println("修改會員資格成功");
			request.getSession(true).setAttribute("status", "seccess");
		}else {
			System.out.println("修改會員資格失敗");
			request.getSession(true).setAttribute("status", "fail");
		}
		request.getRequestDispatcher("/Member/Admin.jsp").forward(request, response);
		
	}

	private void changePwd(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String old_P = request.getParameter("old_P").trim();
		String new_P = request.getParameter("new_P");
		String again_P = request.getParameter("again_P");
		String[] userData = (String[]) request.getSession().getAttribute("userData");
		int userId = Integer.parseInt(userData[3]);
		String userName = userData[0];
		if(old_P.equals(userData[2]) && new_P.equals(again_P)){
			MemberBean chgPwd_data = new MemberBean(new_P, userId, userName);
			if (memberDAO.changePwdByIdAndName(chgPwd_data)) {
				System.out.println("修改密碼成功");
				request.getSession(true).setAttribute("status", "seccess");
				
			}else {
				System.out.println("修改密碼失敗");
				request.getSession(true).setAttribute("status", "fail");
			}
		}else{
			System.out.println("舊密碼不符合或兩次輸入沒有正確，請重新輸入一次");
			request.getSession(true).setAttribute("status", "retry");
		}
		//最後導回頁面
		request.getRequestDispatcher("/Member/MemberSetting.jsp").forward(request, response);
		
	}

	private void register(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		System.out.println("testest");
		name = request.getParameter("name").trim();
		account = request.getParameter("account").trim();
		password = request.getParameter("password").trim();
		status= "一般會員";
		
		MemberBean registerData = new MemberBean(name, account, password, status);
		if (memberDAO.addMember(registerData)) {
			MemberBean memberData = memberDAO.login(registerData);
			System.out.println(memberData.getId());
			request.getSession(true).setAttribute("memberDataForPhoto", memberData);
			System.out.println("登入成功，已放入會員資料");
//			savePhoto(memberData, request, response);
			request.getRequestDispatcher("/Photo").forward(request, response);
			System.out.println("加入會員成功");
		}else {
			System.out.println("加入會員失敗");
			request.getSession(true).setAttribute("status", "fail");
			request.getRequestDispatcher("/Member/Register.jsp").forward(request, response);
		}
	}



	private void login(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		account = request.getParameter("account").trim();
		password = request.getParameter("password").trim();
		MemberBean memberData = memberDAO.login(new MemberBean(account,password));
		if (!memberData.getStatus().equals("登入失敗")) { 
			request.getSession(true).setAttribute("memberData", memberData);
			request.getRequestDispatcher("/Member/LoginSuccess.jsp").forward(request, response);
		}else {
			request.getSession(true).setAttribute("status", "fail");
			request.getRequestDispatcher("/Member/Login.jsp").forward(request, response);
		}
		
		
		
	}



	public void queryAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			MemberBean queryAllData = memberDAO.queryMemberAll();
			request.getSession(true).setAttribute("queryAllData", queryAllData);
			request.getRequestDispatcher("/Member/AdminAllQuery.jsp").forward(request, response);
	}
	
	
}
