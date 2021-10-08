package Ad;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;

@WebServlet("/AdServlet")
public class AdServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
			AdDAO adDAO = new AdDAO(conn);

			// 如果要編碼值為UTF-8
			request.setCharacterEncoding("UTF-8");

			if (request.getParameter("read") != null) {
				processQuery(request, response, adDAO);
			}

			if (request.getParameter("update") != null) {
				processUpdate(request, response, adDAO);
			}
			
			if (request.getParameter("create") != null) {
				processCreate(request, response, adDAO);
			}
			
			if (request.getParameter("delete") != null) {
				processDelete(request, response, adDAO);
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
	private void processUpdate(HttpServletRequest request, HttpServletResponse response, AdDAO adDAO)
			throws SQLException, IOException {
		String aId = request.getParameter("aId");
		String aName = request.getParameter("aName");
		String aPost = request.getParameter("aPost");

		Ad ad = adDAO.findAd(Integer.parseInt(aId));
		if (ad == null)
			showError(response, " can not find this Ad no" + aId);
		else {
			ad.setAname(aName);
			if (adDAO.updateAd(ad))
				showForm(response, ad);
			else
				showError(response, " update failure");
		}
	}
	
	// 查詢資料
	private void processQuery(HttpServletRequest request, HttpServletResponse response, AdDAO adDAO)
			throws SQLException, IOException {
		// 讀取id
		String aId = request.getParameter("aId");

		// 透過DAO元件Access Table
		Ad ad = adDAO.findAd(Integer.parseInt(aId));
		if (ad == null)
			showError(response, " Can not find this Ad no" + aId);
		else
			showForm(response, ad);

	}
	
	//刪除資料
	private void processDelete(HttpServletRequest request, HttpServletResponse response, AdDAO adDAO)
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
		ctxt = new InitialContext();
		
		ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/DB7");

		conn = ds.getConnection();

		AdDAO adconDAO = new AdDAO(conn);
        		
        adconDAO.deleteAd(Integer.parseInt(aId));
		if (adconDAO.deleteAd(Integer.parseInt(aId)))
			showError(response, " Can not delete this Ad no" + aId);
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
	private void processCreate(HttpServletRequest request, HttpServletResponse response, AdDAO adDAO)
			throws SQLException, IOException {
		
		// 讀取id
		int aId = Integer.valueOf(request.getParameter("aId")) ;
		String aName = request.getParameter("aName");
		String aPost = request.getParameter("aPost");
//		System.out.println(aPost);
		
		PrintWriter out = response.getWriter(); 
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		DataSource ds = null;
		InitialContext ctxt = null;
		Connection conn = null;
		
		try {
			
			ctxt = new InitialContext();
			
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/DB7");
			
			conn = ds.getConnection();
			
			AdDAO adconDAO = new AdDAO(conn);
			Ad adcon = new Ad(aId, aName, aPost);
			
			
			if (adconDAO.createAd(adcon)) {
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Result</title>");
				out.println("</head>");
				out.println("<body BGCOLOR='#FDF5E6'>");
				out.println("<h1 ALIGN='CENTER'>Data has been Created.</h1>");
				out.println("</body>");
				out.println("</html>");
				out.close();
			} else {
				showError(response, " Can not create this Ad no" + aId);
			}
			
		} catch (NamingException ne) {
			System.out.println("Naming Service Lookup Exception");
		} catch (SQLException e) {
			System.out.println("Database Error!");
		}
		
	}
	
	
	
	

	private void showError(HttpServletResponse response, String message) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Result</title>");
		out.println("</head>");
		out.println("<body BGCOLOR='#FDF5E6'>");
		out.println("message:" + message);
		out.println("</body>");
		out.println("</html>");
		out.close();
	}

	private void showForm(HttpServletResponse response, Ad ad) throws IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Result</title>");
		out.println("<style>");
		out.println("form {width: 70%; margin: 20px auto; text-align: center; }");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form action='./AdServlet'>");
		out.println("<h1>結果</h1>");
		out.println("aId  :<input type='text' name='aId' value='" + ad.getAid() + "'><br>");
		out.println("aName:<input type='text' name='aName' value='" + ad.getAname() + "'><br>");
		out.println("aPost:<input type='text' name='aPost' value='" + ad.getApost() + "'><br>");
		out.println("<input type='submit' name='create' value='新增'>");
		out.println("<input type='submit' name='read' value='查詢'>");
		out.println("<input type='submit' name='update' value='修改'>");
		out.println("<input type='submit' name='delete' value='刪除'>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}

}