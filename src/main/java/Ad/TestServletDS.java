package Ad;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
@WebServlet("/TestServletDS")
public class TestServletDS extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DataSource ds = null;
		InitialContext ctxt = null;
		Connection conn = null;

		try {

			// 建立Context Object,連到JNDI Server
			ctxt = new InitialContext();

			// 使用JNDI API找到DataSource
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/DB7");

			// 向DataSource要Connection
			conn = ds.getConnection();

			// 建立Database Access Object,負責Table的Access
			TestDAO testDAO = new TestDAO(conn);

			// 如果要編碼值為UTF-8
			request.setCharacterEncoding("UTF-8");

			if (request.getParameter("QUERY") != null) {
				processQuery(request, response, testDAO);
			}

			if (request.getParameter("UPDATE") != null) {
				processUpdate(request, response, testDAO);
			}
			if (request.getParameter("CREATE") != null) {
				processCreate(request, response, testDAO);
			}
			if (request.getParameter("DELETE") != null) {
				processDelete(request, response, testDAO);
			}
		} catch (NamingException ne) {
			System.out.println("Naming Service Lookup Exception");
		} catch (SQLException e) {
			System.out.println("Database Connection Error");
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}
	}
	
	// 修改資料
	private void processUpdate(HttpServletRequest request, HttpServletResponse response, TestDAO testDAO)
			throws SQLException, IOException {
		System.out.println("debug");
		String aId = request.getParameter("aId");
		String aName = request.getParameter("aName");
		String aPost = request.getParameter("aPost");

		Test test = testDAO.findTest(Integer.parseInt(aId));
		if (test == null)
			showError(response, " can not find this Ad no" + aId);
		else {
			test.setAname(aName);
			if (testDAO.updateTest(test))
				showForm(response, test);
			else
				showError(response, " update failure");
		}
	}
	
	// 查詢資料
	private void processQuery(HttpServletRequest request, HttpServletResponse response, TestDAO testDAO)
			throws SQLException, IOException {
		System.out.println("debug q");
		// 讀取id
		String aId = request.getParameter("aId");
		
		// 透過DAO元件Access Table
		Test test = testDAO.findTest(Integer.parseInt(aId));
		if (test == null)
			showError(response, " can not find this Ad no" + aId);
		else
			showForm(response, test);

	}
	
	//刪除資料
	private void processDelete(HttpServletRequest request, HttpServletResponse response, TestDAO testDAO)
			throws SQLException, IOException {
		// 讀取id
		String aId = request.getParameter("aId");
		
		PrintWriter out = response.getWriter(); 
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
		
        DataSource ds = null;
		InitialContext ctxt = null;
		Connection conn = null;
		try {
			
    	// 建立Context Object,連到JNDI Server
		ctxt = new InitialContext();

		// 使用JNDI API找到DataSource
		ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/DB7");

		// 向DataSource要Connection
		conn = ds.getConnection();

		// 建立Database Access Object,負責Table的Access
		// 透過DAO元件Access Table
		TestDAO testconDAO = new TestDAO(conn);
        		
        testconDAO.deleteTest(Integer.parseInt(aId));
		if (testconDAO.deleteTest(Integer.parseInt(aId)))
			showError(response, " can not find this Ad no" + aId);
		else 
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>Result</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY BGCOLOR='#FDF5E6'>");
		out.println("<H1 ALIGN='CENTER'>Data has been deleted.</H1>");
		out.println("</BODY>");
		out.println("</HTML>");
		out.close();
		} catch (NamingException ne) {
			System.out.println("Naming Service Lookup Exception");
		} catch (SQLException e) {
			System.out.println("Database Error!");
		}
		
	}
	
	// 新增資料
	private void processCreate(HttpServletRequest request, HttpServletResponse response, TestDAO testDAO)
			throws SQLException, IOException {
		
		// 讀取id
		int aId = Integer.valueOf(request.getParameter("aId")) ;
		String aName = request.getParameter("aName");
		String aPost = request.getParameter("aPost");
		System.out.println(aPost);
		
		PrintWriter out = response.getWriter(); 
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		DataSource ds = null;
		InitialContext ctxt = null;
		Connection conn = null;
		
		try {
			
			// 建立Context Object,連到JNDI Server
			ctxt = new InitialContext();
			
			// 使用JNDI API找到DataSource
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/DB7");
			
			// 向DataSource要Connection
			conn = ds.getConnection();
			
			// 建立Database Access Object,負責Table的Access
			// 透過DAO元件Access Table
			TestDAO testconDAO = new TestDAO(conn);
			Test testcon = new Test(aId, aName, aPost);
			
			
			if (testconDAO.createTest(testcon))
				showError(response, " can not find this Ad no" + aId);
			else 
				out.println("<HTML>");
			out.println("<HEAD>");
			out.println("<TITLE>Result</TITLE>");
			out.println("</HEAD>");
			out.println("<BODY BGCOLOR='#FDF5E6'>");
			out.println("<H1 ALIGN='CENTER'>Data has been Created.</H1>");
			out.println("</BODY>");
			out.println("</HTML>");
			out.close();
		} catch (NamingException ne) {
			System.out.println("Naming Service Lookup Exception");
		} catch (SQLException e) {
			System.out.println("Database Error!");
		}
		
	}
	
	

	private void showError(HttpServletResponse response, String message) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>Result</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY BGCOLOR='#FDF5E6'>");
		out.println("message:" + message);
		out.println("</BODY>");
		out.println("</HTML>");
		out.close();
	}

	private void showForm(HttpServletResponse response, Test test) throws IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>Result</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY BGCOLOR='#FDF5E6'>");
		out.println("<H1 ALIGN='CENTER'>Result</H1>");
		out.println("<FORM ACTION='./TestServletDS'>");
		out.println("aId:<INPUT TYPE='TEXT' NAME='aId' VALUE='" + test.getAid() + "'><BR>");
		out.println("aName:  <INPUT TYPE='TEXT' NAME='aName' VALUE='" + test.getAname() + "'><BR>");
		out.println("aPost:  <INPUT TYPE='TEXT' NAME='aName' VALUE='" + test.getApost() + "'><BR>"); //新加的
		out.println("  <CENTER>");
		out.println("<INPUT NAME='QUERY'  TYPE='SUBMIT' VALUE='QUERY'>");
		out.println("<INPUT NAME='UPDATE' TYPE='SUBMIT' VALUE='UPDATE'>");
		out.println("<INPUT NAME='CREATE' TYPE='SUBMIT' VALUE='CREATE'>"); //新加的
		out.println("<INPUT NAME='DELETE' TYPE='SUBMIT' VALUE='DELETE'>"); //新加的
	    
	    
		out.println("</CENTER>");
		out.println("</FORM>");
		out.println("</BODY>");
		out.println("</HTML>");
		out.close();
	}

}