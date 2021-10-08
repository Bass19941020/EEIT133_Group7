package Activity;
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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/ActivityController")
/**
 * Servlet implementation class RegisterServlet
 */
public class ActivityController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	 private static final String CHARSET_CODE = "UTF-8";
	 public void init(ServletConfig config) throws ServletException
	 {
	   super.init(config);
	 }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    request.setCharacterEncoding(CHARSET_CODE);
	    response.setContentType(CONTENT_TYPE);

	    // To prevent caching 
	   response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
	   response.setHeader("Pragma","no-cache"); // HTTP 1.0
	   response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server

	    
	   if (request.getParameter("submit")!=null)
	     gotoSubmitProcess(request, response);
	   else if (request.getParameter("confirm")!=null)
	     gotoConfirmProcess(request, response);
	   else if(request.getParameter("signup")!=null) {
		   request.getRequestDispatcher("/Activity/RegisterForm.jsp").forward(request,response);
	}
	   else if (request.getParameter("check")!=null) {
		   request.getRequestDispatcher("/Activity/check.jsp").forward(request,response);
		
	}
	   else if (request.getParameter("check1")!=null) {
		   gotoCheckProcess(request, response);
		
	}
	   else if (request.getParameter("delete")!=null) {
		   request.getRequestDispatcher("/Activity/delete.jsp").forward(request,response);
	}
	   else if (request.getParameter("delete1")!=null) {
		   gotoDelteCheckProcess(request, response);
		   
		   
		
	}
	   else if (request.getParameter("revise")!=null) {
		   request.getRequestDispatcher("/Activity/revise.jsp").forward(request,response);
		
	}
	   else if (request.getParameter("revise1")!=null) {
		 gotoReviseCheckProcess(request, response);
		   
		
	}
	   else if (request.getParameter("revise2")!=null) {
		  gotoConfirmReviseResult(request, response);
		
	}
	   else if (request.getParameter("revise3")!=null) {
		   gotoReviseProcess(request, response);
		
	}
	   else if (request.getParameter("delete2")!=null) {
		gotoDeleteProcess(request, response);
	}
	   else if (request.getParameter("return")!=null) {
		   request.getRequestDispatcher("/Activity/RegisterForm2.jsp").forward(request,response);
	}
	}
	
	 public void gotoSubmitProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	  {
	    String name;
	    String birthyear, birthmonth, birthday;
	    String id;
	    String zipcode;
	    String address;
	    String phone;
	    String mailaddress;
	   
	    name = request.getParameter("name").trim();
	    birthyear = request.getParameter("birthyear").trim();
	    birthmonth = request.getParameter("birthmonth").trim();
	    birthday = request.getParameter("birthday").trim();
	    id = request.getParameter("id").trim();
	    zipcode = request.getParameter("zipcode").trim();
	    address = request.getParameter("address").trim();
	    phone = request.getParameter("phone").trim();
	    mailaddress = request.getParameter("mailaddress").trim();
	    ActivityBean reg_student =  new ActivityBean(name, birthyear, birthmonth, birthday, id, zipcode, address, phone, mailaddress);
	    request.getSession(true).setAttribute("reg_student",reg_student);
	    request.getRequestDispatcher("/Activity/DisplayStudent.jsp").forward(request,response);
	  }

	  public void gotoConfirmProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	  {

	    DataSource ds = null;
	    InitialContext ctxt = null;
	    Connection conn = null;
	    
	    try {
	     
	      //建立Context Object,連到JNDI Server	
	      ctxt = new InitialContext();

	      //使用JNDI API找到DataSource
	      ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/DB7");
	      //ds = ( DataSource ) ctxt.lookup("jdbc/OracleXE");
	      //向DataSource要Connection
	      conn = ds.getConnection();

	      //建立Database Access Object,負責Table的Access
	      ActivityDAO ActivityDAO = new ActivityDAO(conn);
	      ActivityBean studentData = (ActivityBean)request.getSession(true).getAttribute("reg_student");
	      if (ActivityDAO.insertActivitySignUp(studentData))
	        {
	          System.out.println("Get some SQL commands done!");
	          request.getSession(true).invalidate();
	          request.getRequestDispatcher("/Activity/Thanks.jsp").forward(request,response);
	        }
	    } catch (NamingException ne) {
	      System.out.println("Naming Service Lookup Exception");  
	    } catch (SQLException e) {
	      System.out.println("Database Connection Error"); 
	    } finally {
	      try {
	        if (conn != null) conn.close();
	      } catch (Exception e) {
	        System.out.println("Connection Pool Error!");
	      }
	    }
	           
	  }
	  public void gotoCheckProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	  {

		   response.setCharacterEncoding("UTF-8");
	        response.setContentType("text/html;charset=UTF-8");
	        request.setCharacterEncoding("UTF-8");
	        PrintWriter out = response.getWriter();
	        DataSource ds = null;
		    InitialContext ctxt = null;
		    Connection conn = null;
		    

	        String id = request.getParameter("id").trim();
	        System.out.println(id);
	       

	       
	          
	            try {
	            	   //建立Context Object,連到JNDI Server	
	      	      ctxt = new InitialContext();

	      	      //使用JNDI API找到DataSource
	      	      ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/DB7");
	      	      //ds = ( DataSource ) ctxt.lookup("jdbc/OracleXE");
	      	      //向DataSource要Connection
	      	      conn = ds.getConnection();
	      	      String sql="select * from ActivitySignUp where Id=?";

	      	     
	      	      
	      	    PreparedStatement ps = conn.prepareStatement(sql);
	      	    ps.setString(1, id);
	            
	            ResultSet rs = ps.executeQuery();
	       
	            
	            
	            if (rs.next()) {
		            out.println("<!DOCTYPE html>");
			        out.println("<html>");
			        out.println("<head>");
			        out.println("<title>查詢結果</title>");
			        out.println("</head>");
			        out.println("<body>");
			        out.println("<h1>查詢結果如下:</h1>");
			        out.println("<p>姓名:"+rs.getString(1)+"</p>\n");
			        out.println("<p>身分證字號:"+rs.getString(2)+"</p>\n");
			        out.println("<p>出生日期:"+"民國:"+rs.getInt(3)+"年"+rs.getInt(4)+"月"+rs.getInt(5)+"號"+"</p>\n");
			        out.println("<p>郵遞區號:"+rs.getInt(6)+"</p>\n");
			        out.println("<p>地址:"+rs.getString(7)+"</p>\n");
			        out.println("<p>電話:"+rs.getInt(8)+"</p>\n");
			        out.println("<p>信箱:"+rs.getString(9)+"</p>\n");
			        out.println("</body>");
			        out.println("</html>");
				}
	            else {request.getRequestDispatcher("/Activity/NoResult.jsp").forward(request,response);
					
				}
	            String name;
	    	    String birthyear, birthmonth, birthday,zipcode,phone;
	    	    String address;
	    	    String mailaddress;
	    	   
	    	    name = rs.getString(1);
	    	    birthyear =String.valueOf(rs.getInt(3));
	    	    birthmonth =String.valueOf(rs.getInt(4));
	    	    birthday = String.valueOf(rs.getInt(5));
	    	    zipcode = String.valueOf(rs.getInt(6));
	    	    address = rs.getString(7);
	    	    phone = String.valueOf(rs.getInt(8));
	    	    mailaddress = rs.getString(9);
	    	    ActivityBean reg_student =  new ActivityBean(name, birthyear, birthmonth, birthday, id, zipcode, address, phone, mailaddress);
	    	    request.getSession(true).setAttribute("reg_student",reg_student);
	    	    request.getRequestDispatcher("/Activity/DisplayCheckResult.jsp").forward(request,response);
	         


		        rs.close();
		        ps.close();
		        
	            } 
	            catch (NamingException ne) {
	      	      System.out.println("Naming Service Lookup Exception");  
	      	    } catch (SQLException e) {
	      	      e.printStackTrace();
	      	    } finally {
	      	      try {
	      	        if (conn != null) conn.close();
	      	        
	      	      
	      	      } catch (Exception e) {
	      	        System.out.println("Connection Pool Error!");
	      	      }
	      	    }

}
	  
	  public void gotoDelteCheckProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	  {

	        response.setCharacterEncoding("UTF-8");
	        response.setContentType("text/html;charset=UTF-8");
	        request.setCharacterEncoding("UTF-8");
	        PrintWriter out = response.getWriter();
	        DataSource ds = null;
		    InitialContext ctxt = null;
		    Connection conn = null;
		    

	        String id = request.getParameter("id").trim();
	        System.out.println(id);
	       
 
	       
	          
	            try {
	            	   //建立Context Object,連到JNDI Server	
	      	      ctxt = new InitialContext();

	      	      //使用JNDI API找到DataSource
	      	      ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/DB7");
	      	      //ds = ( DataSource ) ctxt.lookup("jdbc/OracleXE");
	      	      //向DataSource要Connection
	      	      conn = ds.getConnection();
	      	      String sql="select * from ActivitySignUp where Id=?";

	      	     
	      	      
	      	    PreparedStatement ps = conn.prepareStatement(sql);
	      	    ps.setString(1, id);
	            
	            ResultSet rs = ps.executeQuery();
	       
	            
	            
	            if (rs.next()) {
		            out.println("<!DOCTYPE html>");
			        out.println("<html>");
			        out.println("<head>");
			        out.println("<title>查詢結果</title>");
			        out.println("</head>");
			        out.println("<body>");
			        out.println("<h1>查詢結果如下:</h1>");
			        out.println("<p>姓名:"+rs.getString(1)+"</p>\n");
			        out.println("<p>身分證字號:"+rs.getString(2)+"</p>\n");
			        out.println("<p>出生日期:"+"民國:"+rs.getInt(3)+"年"+rs.getInt(4)+"月"+rs.getInt(5)+"號"+"</p>\n");
			        out.println("<p>郵遞區號:"+rs.getInt(6)+"</p>\n");
			        out.println("<p>地址:"+rs.getString(7)+"</p>\n");
			        out.println("<p>電話:"+rs.getInt(8)+"</p>\n");
			        out.println("<p>信箱:"+rs.getString(9)+"</p>\n");
			        out.println("</body>");
			        out.println("</html>");
				}
	            else {request.getRequestDispatcher("/Activity/NoResult.jsp").forward(request,response);
					
				}
	            String name;
	    	    String birthyear, birthmonth, birthday,zipcode,phone;
	    	    String address;
	    	    String mailaddress;
	    	   
	    	    name = rs.getString(1);
	    	    birthyear =String.valueOf(rs.getInt(3));
	    	    birthmonth =String.valueOf(rs.getInt(4));
	    	    birthday = String.valueOf(rs.getInt(5));
	    	    zipcode = String.valueOf(rs.getInt(6));
	    	    address = rs.getString(7);
	    	    phone = String.valueOf(rs.getInt(8));
	    	    mailaddress = rs.getString(9);
	    	    ActivityBean reg_student =  new ActivityBean(name, birthyear, birthmonth, birthday, id, zipcode, address, phone, mailaddress);
	    	    request.getSession(true).setAttribute("reg_student",reg_student);
	    	    request.getRequestDispatcher("/Activity/DisplayDeleteInformation.jsp").forward(request,response);
	         


		        rs.close();
		        ps.close();
		        
	            } 
	            catch (NamingException ne) {
	      	      System.out.println("Naming Service Lookup Exception");  
	      	    } catch (SQLException e) {
	      	      e.printStackTrace();
	      	    } finally {
	      	      try {
	      	        if (conn != null) conn.close();
	      	        
	      	      
	      	      } catch (Exception e) {
	      	        System.out.println("Connection Pool Error!");
	      	      }
	      	    }

}
	  public void gotoDeleteProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	  {

	        response.setCharacterEncoding("UTF-8");
	        response.setContentType("text/html;charset=UTF-8");
	        request.setCharacterEncoding("UTF-8");
//	        PrintWriter out = response.getWriter();
	        DataSource ds = null;
		    InitialContext ctxt = null;
		    Connection conn = null;
		    
		    try { 
		    	  //建立Context Object,連到JNDI Server	
			      ctxt = new InitialContext();

			      //使用JNDI API找到DataSource
			      ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/DB7");
			      //ds = ( DataSource ) ctxt.lookup("jdbc/OracleXE");
			      //向DataSource要Connection
			      conn = ds.getConnection();
		    
		    	
		    ActivityDAO ActivityDAO = new ActivityDAO(conn);
		    ActivityBean studentData = (ActivityBean)request.getSession(true).getAttribute("reg_student");         
	        String id = studentData.getId(); 
	        System.out.println(id);
	        if (ActivityDAO.deleteActivitySignUp(studentData))
	        {
	          System.out.println("Get some SQL commands done!");
	          request.getSession(true).invalidate();
	          request.getRequestDispatcher("/Activity/DeleteThanks.jsp").forward(request,response);
	        }
			} catch (NamingException ne) {
			      System.out.println("Naming Service Lookup Exception");  
			    } catch (SQLException e) {
			      System.out.println("Database Connection Error"); 
			    } finally {
			      try {
			        if (conn != null) conn.close();
			      } catch (Exception e) {
			        System.out.println("Connection Pool Error!");
			      }
			    }
	        	  
	         
	       } 


	    
	    
	        
	        
	       
 
	       
	          


	
	
	  
	  public void gotoReviseProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	  {
		  DataSource ds = null;
		    InitialContext ctxt = null;
		    Connection conn = null;
		    
		    try {
		     
		      //建立Context Object,連到JNDI Server	
		      ctxt = new InitialContext();

		      //使用JNDI API找到DataSource
		      ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/DB7");
		      //ds = ( DataSource ) ctxt.lookup("jdbc/OracleXE");
		      //向DataSource要Connection
		      conn = ds.getConnection();

		      //建立Database Access Object,負責Table的Access
		      ActivityDAO ActivityDAO = new ActivityDAO(conn);
		      ActivityBean studentData = (ActivityBean)request.getSession(true).getAttribute("reg_student");
		      if (ActivityDAO.reviseSignUpInformation(studentData))
		        {
		          System.out.println("Get some SQL commands done!");
		          request.getSession(true).invalidate();
		          request.getRequestDispatcher("/Activity/ReviseThanks.jsp").forward(request,response);
		        }
		    } catch (NamingException ne) {
		      System.out.println("Naming Service Lookup Exception");  
		    } catch (SQLException e) {
		      System.out.println("Database Connection Error"); 
		    } finally {
		      try {
		        if (conn != null) conn.close();
		      } catch (Exception e) {
		        System.out.println("Connection Pool Error!");
		      }
		    }
		           
	      
}
	  
	  public void gotoReviseCheckProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	  {

	        response.setCharacterEncoding("UTF-8");
	        response.setContentType("text/html;charset=UTF-8");
	        request.setCharacterEncoding("UTF-8");
	       
	        DataSource ds = null;
		    InitialContext ctxt = null;
		    Connection conn = null;
		    

	        String id = request.getParameter("id").trim();
	        System.out.println(id);
	       
 
	       
	          
	            try {
	            	   //建立Context Object,連到JNDI Server	
	      	      ctxt = new InitialContext();

	      	      //使用JNDI API找到DataSource
	      	      ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/DB7");
	      	      //ds = ( DataSource ) ctxt.lookup("jdbc/OracleXE");
	      	      //向DataSource要Connection
	      	      conn = ds.getConnection();
	      	      String sql="select * from ActivitySignUp where Id=?";

	      	     
	      	      
	      	    PreparedStatement ps = conn.prepareStatement(sql);
	      	    ps.setString(1, id);
	            
	            ResultSet rs = ps.executeQuery();
	       
	            
	            
	            if (rs.next()) {
		           
			       rs.getString(1);
			       rs.getString(2);
			        rs.getInt(3);
			        rs.getInt(4);
			        rs.getInt(5);
			        rs.getInt(6);
			        rs.getString(7);
			        rs.getInt(8);
			        rs.getString(9);
			    
				}
	            else {request.getRequestDispatcher("/Activity/NoResult.jsp").forward(request,response);
					
				}
	            String name;
	    	    String birthyear, birthmonth, birthday,zipcode,phone;
	    	    String address;
	    	    String mailaddress;
	    	   
	    	    name = rs.getString(1);
	    	    birthyear =String.valueOf(rs.getInt(3));
	    	    birthmonth =String.valueOf(rs.getInt(4));
	    	    birthday = String.valueOf(rs.getInt(5));
	    	    zipcode = String.valueOf(rs.getInt(6));
	    	    address = rs.getString(7);
	    	    phone = String.valueOf(rs.getInt(8));
	    	    mailaddress = rs.getString(9);
	    	    ActivityBean reg_student =  new ActivityBean(name, birthyear, birthmonth, birthday, id, zipcode, address, phone, mailaddress);
	    	    request.getSession(true).setAttribute("reg_student",reg_student);
	    	    request.getRequestDispatcher("/Activity/DisplayReviseInformation.jsp").forward(request,response);
	         


		        rs.close();
		        ps.close();
		        
	            } 
	            catch (NamingException ne) {
	      	      System.out.println("Naming Service Lookup Exception");  
	      	    } catch (SQLException e) {
	      	      e.printStackTrace();
	      	    } finally {
	      	      try {
	      	        if (conn != null) conn.close();
	      	        
	      	      
	      	      } catch (Exception e) {
	      	        System.out.println("Connection Pool Error!");
	      	      }
	      	    }

}
	  public void gotoConfirmReviseResult(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  String name;
		    String birthyear, birthmonth, birthday;
		    String id;
		    String zipcode;
		    String address;
		    String phone;
		    String mailaddress;
		   
		    name = request.getParameter("name").trim();
		    birthyear = request.getParameter("birthyear").trim();
		    birthmonth = request.getParameter("birthmonth").trim();
		    birthday = request.getParameter("birthday").trim();
		    id = request.getParameter("id").trim();
		    zipcode = request.getParameter("zipcode").trim();
		    address = request.getParameter("address").trim();
		    phone = request.getParameter("phone").trim();
		    mailaddress = request.getParameter("mailaddress").trim();
		    ActivityBean reg_student =  new ActivityBean(name, birthyear, birthmonth, birthday, id, zipcode, address, phone, mailaddress);
		    request.getSession(true).setAttribute("reg_student",reg_student);
		    request.getRequestDispatcher("/Activity/DisplayReviseResult.jsp").forward(request,response);
	}










}


