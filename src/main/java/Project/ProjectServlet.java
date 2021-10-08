package Project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.jasper.compiler.JspUtil;

/**
 * Servlet implementation class ProjectServlet
 */
@WebServlet("/ProjectServlet")
public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String active = "";
	DataSource ds = null;
	InitialContext ctxt = null;
	Connection conn = null;
	ProjectDAO projectDAO = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";

	public ProjectServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(CHARSET_CODE);
		response.setContentType(CONTENT_TYPE);

		if (request.getParameter("goToAddData") != null) {// 跳轉到提出計畫
			goToAddData(request, response);

		} else if (request.getParameter("addProject") != null) {// 進行資料新增
			addProject(request, response);
		}

		else if (request.getParameter("deleteProject") != null) {// 進行資料刪除
			deleteProject(request, response);
		}

		else if (request.getParameter("goToSelect") != null) {// 跳轉到單筆資料查詢
			goToSelect(request, response);

		}

		else if (request.getParameter("selectProject") != null) {// 查詢單筆資料

			selectProject(request, response);

		}

		else if (request.getParameter("editProject") != null) {// 在查詢後結果按編輯按鈕

			editProject(request, response);

		}

		else if (request.getParameter("save") != null) {// 編輯後存檔

			saveProject(request, response);

		}

		else if (request.getParameter("goToSelectAll") != null) {// 進行整個table資料查詢
			goToSelectAll(request, response);
		}

		else if (request.getParameter("backHome") != null) {// 回首頁
			goToHome(request, response);

		}

	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			// 建立Context Object,連到JNDI Server
			ctxt = new InitialContext();

			// 使用JNDI API找到DataSource
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/DB7");
			// ds = ( DataSource ) ctxt.lookup("jdbc/OracleXE");
			// 向DataSource要Connection
			conn = ds.getConnection();
			// 建立Database Access Object,負責Table的Access
			projectDAO = new ProjectDAO(conn);
			System.out.println("SQL-Serever連線成功");

		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		if (conn != null) {
			try {
				conn.close();
				System.out.println("SQL-Serever關閉連線");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void goToAddData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/Project/addData.jsp").forward(request, response);
	}

	public void goToDeleteData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/Project/deleteProject.jsp").forward(request, response);
	}

	public void goToSelect(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/Project/selectProject.jsp").forward(request, response);
	}

	public void goToHome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/Project/menu.html").forward(request, response);
	}

	public void addProject(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding(CHARSET_CODE);
		response.setContentType(CONTENT_TYPE);

		String pName;// 計畫名稱
		int pTarget;// 計畫目標金額
		String pDescribe;// 計畫介紹
		String pSDate;

		pName = request.getParameter("pName").trim();
		pTarget = Integer.parseInt(request.getParameter("pTarget").trim());
		pDescribe = request.getParameter("pDescribe").trim();
		pSDate = request.getParameter("pSDate").trim().replace("T", " ");
		
		

		ProjectBean projectData = new ProjectBean(pName, pTarget, pDescribe,pSDate);
		// request.getSession(true).setAttribute("reg_projectBean", reg_projectBean);//
		// 把資料存在session中，前標籤，後資料
		if (projectDAO.insertProject(projectData)) {
			System.out.println("Get some SQL commands done!");
			request.getSession(true).invalidate();
			request.getRequestDispatcher("/Project/done.jsp").forward(request, response);
		}
		
	}

	public void saveProject(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding(CHARSET_CODE);
		response.setContentType(CONTENT_TYPE);
		String pID = (String) request.getSession(true).getAttribute("ProjectID");// 拿到要修改的ID

		String pName;// 計畫名稱
		int pTarget;// 計畫目標金額
		String pDescribe;// 計畫介紹
		String pSDate;// 計畫開始時間

		pName = request.getParameter("editPName").trim();
		pTarget = Integer.parseInt(request.getParameter("editPTarget").trim());
		pDescribe = request.getParameter("editPDescribe").trim();
		pSDate = request.getParameter("editPSDate").trim().replace("T", " ");
		

		ProjectBean projectData = new ProjectBean(pID, pName, pTarget, pDescribe,pSDate);
		// request.getSession(true).setAttribute("reg_projectBean", reg_projectBean);//
		// 把資料存在session中，前標籤，後資料

		int countDeleteProject = projectDAO.updateProject(projectData);
		if (countDeleteProject >= 0) {
			System.out.println("Update datas:" + countDeleteProject);
			request.getSession(true).invalidate();
			request.getRequestDispatcher("/Project/done.jsp").forward(request, response);
		}
		
	}

	public void selectProject(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding(CHARSET_CODE);
		response.setContentType(CONTENT_TYPE);

		String pID;
		pID = request.getParameter("pID").trim();

		ProjectBean projectData = new ProjectBean(pID);
		ProjectBean selectPB = projectDAO.selectProject(projectData);
		String rsID = selectPB.getpID();
		if (rsID != null) {// 看看有沒有找到此資料
			// 把data資料全部放到session，傳到reg_allProject空間裡
			request.getSession(true).setAttribute("ProjectBean", selectPB);
			request.getSession(true).setAttribute("ProjectID", pID);// 把這筆ID丟到Session去傳遞
			request.getRequestDispatcher("/Project/selectProjectResult.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/Project/noData.jsp").forward(request, response);
		}
		
	}

	public void editProject(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding(CHARSET_CODE);
		response.setContentType(CONTENT_TYPE);

		ProjectBean projectData = (ProjectBean) request.getSession(true).getAttribute("ProjectBean");// 拿到完整資料
		
		// request.getSession(true).setAttribute("reg_projectBean", reg_projectBean);//
		// 把資料存在session中，前標籤，後資料

		request.getRequestDispatcher("/Project/editProject.jsp").forward(request, response);
		
	}

	public void deleteProject(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding(CHARSET_CODE);
		response.setContentType(CONTENT_TYPE);

		ProjectBean projectData = (ProjectBean) request.getSession(true).getAttribute("ProjectBean");// 拿到完整資料

		// request.getSession(true).setAttribute("reg_projectBean", reg_projectBean);//
		// 把資料存在session中，前標籤，後資料

		int countDeleteProject = projectDAO.deleteProject(projectData);
		if (countDeleteProject >= 0) {
			System.out.println("delete datas:" + countDeleteProject);
			request.getSession(true).invalidate();
			request.getRequestDispatcher("/Project/done.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/Project/noData.jsp").forward(request, response);
		}
		
	}

	public void goToSelectAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding(CHARSET_CODE);
		response.setContentType(CONTENT_TYPE);

		List<ProjectBean> data = projectDAO.selectAll();

		// 把data資料全部放到session，傳到reg_allProject空間裡
		request.getSession(true).setAttribute("projectList", data);
		request.getRequestDispatcher("/Project/projectList.jsp").forward(request, response);
		
	}

}
