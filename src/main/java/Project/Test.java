package Project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class Test
 */
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Test() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			InitialContext initCont = new InitialContext();
			DataSource ds = (DataSource) initCont.lookup("java:comp/env/jdbc/DB7");
			Connection conn = ds.getConnection();
			String sqlString="select * from test";
			PreparedStatement preState = conn.prepareStatement(sqlString);
			ResultSet rs = preState.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt(1));
			}
			
			System.out.println("OK");
			preState.close();
			conn.close();
			initCont.close();
			
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
