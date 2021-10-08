package Sponsorship;

import java.io.IOException;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.ArrayList;
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

/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DataSource ds = null;
	InitialContext ctxt = null;
	Connection conn = null;
	PaymentDAO pDao = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			ctxt = new InitialContext();
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/DB7");
			conn = ds.getConnection();
			pDao = new PaymentDAO(conn);
			System.out.println("開啟sql-server連線成功");
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		if (conn != null) {
			try {
				conn.close();
				System.out.println("關閉sql-server連線");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public PaymentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// To prevent caching
		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", -1); // Prevents caching at the proxy server
		try {
			if (request.getParameter("submit") != null)
				insertSubmit(request, response);
			else if (request.getParameter("confirm") != null)
				insertConfirm(request, response);
			else if (request.getParameter("dsearch") != null)
				deleteSubmit(request, response);
			else if (request.getParameter("deleteconfirm") != null)
				deleteConfirm(request, response);
			else if (request.getParameter("updatelookup") != null)
				updateLookUp(request, response);
			else if (request.getParameter("updateconfirm") != null)
				updateOrder(request, response);
			else if (request.getParameter("searchorder") != null)
				searchOrder(request, response);
			else if (request.getParameter("searchall") != null)
				searchAll(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// 新增訂單
	public void insertSubmit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		int trackingNo = Integer.parseInt(request.getParameter("trackingno").trim());
		String sponsor = request.getParameter("sponsor").trim();
		int projectNo = Integer.parseInt(request.getParameter("projectno").trim());
		String projectName = request.getParameter("project").trim();
		int amount = Integer.parseInt(request.getParameter("amount").trim());
		PaymentBean paymentNew = new PaymentBean(trackingNo, sponsor, projectNo, projectName, amount);
		PaymentBean newBean = pDao.searchOrder("sID", paymentNew.getOrderNumber());
		if (newBean.getSponsorName() == null) {
			request.getSession(true).setAttribute("paymentInsert", paymentNew);
			request.getRequestDispatcher("/Sponsorship/DisplayPayment.jsp").forward(request, response);
		} else if (newBean.getOrderNumber() == paymentNew.getOrderNumber()
				&& newBean.getSponsorName().equals(paymentNew.getSponsorName())
				&& newBean.getProjectID() == paymentNew.getProjectID()
				&& newBean.getProjectName().equals(paymentNew.getProjectName())
				&& newBean.getAmount() == paymentNew.getAmount()) {
			request.getRequestDispatcher("/Sponsorship/RepeatOrder.html").forward(request, response);
		} else {
			request.getRequestDispatcher("/Sponsorship/Repeat.html").forward(request, response);
		}
	}

	// 確認新增
	public void insertConfirm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		PaymentBean p = (PaymentBean) request.getSession(true).getAttribute("paymentInsert");
		if (pDao.addOrder(p)) {
			System.out.println("Get some SQL commands done!");
			request.getSession(true).invalidate();
			request.getRequestDispatcher("/Sponsorship/CreateSucess.html").forward(request, response);
		}

	}

	// 刪除訂單查詢
	public void deleteSubmit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String deleteID = request.getParameter("deleteorderid");
		PaymentBean dBean = pDao.searchOrder("sID", deleteID);
		if (dBean.getSponsorName() != null) {
			request.getSession(true).setAttribute("paymentRead", dBean);
			request.getRequestDispatcher("/Sponsorship/DeleteConfirm.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/Sponsorship/Wrong.html").forward(request, response);
		}

	}

	// 刪除訂單確認
	public void deleteConfirm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		PaymentBean dBean = (PaymentBean) request.getSession(true).getAttribute("paymentRead");
		int deleteID = dBean.getOrderNumber();
		pDao.deleteOrder(deleteID);
		request.getRequestDispatcher("/Sponsorship/DeleteSucess.html").forward(request, response);
	}

	// 查詢欲修改的訂單

	public void updateLookUp(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String updateTarget = request.getParameter("updateid");
		PaymentBean sBean = pDao.searchOrder("sID", updateTarget);
		if (sBean.getSponsorName() != null) {
			request.getSession(true).setAttribute("paymentRead", sBean);
			request.getRequestDispatcher("/Sponsorship/Update.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/Sponsorship/Wrong.html").forward(request, response);
		}

	}

	// 修改訂單

	public void updateOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		int updateID = Integer.parseInt(request.getParameter("updateid"));
		String updateSponor = request.getParameter("updatename");
		int updatePID = Integer.parseInt(request.getParameter("updatepid"));
		String updatePName = request.getParameter("updatepname");
		int updateAmount = Integer.parseInt(request.getParameter("updateamount"));

		pDao.updateOrder("sID", updateID, updateID);
		pDao.updateOrder("sName", updateSponor, updateID);
		pDao.updateOrder("sPID", updatePID, updateID);
		pDao.updateOrder("sPName", updatePName, updateID);
		pDao.updateOrder("sAmount", updateAmount, updateID);

		request.getRequestDispatcher("/Sponsorship/UpdateSucess.jsp").forward(request, response);
	}

	// 查詢訂單
	public void searchOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String columnName = request.getParameter("select");
		// 下拉是選單 select option
		String searchID = request.getParameter("searchid");
		ArrayList<PaymentBean> list = pDao.searchOrder1(columnName, searchID);
		if (!list.isEmpty()) {
			System.out.println(list.get(0).getSponsorName());
			request.getSession(true).setAttribute("paymentRead", list);
			System.out.println("debug");
			request.getRequestDispatcher("/Sponsorship/SearchResult.jsp").forward(request, response);
		} else {
			System.out.println("error");
			request.getRequestDispatcher("/Sponsorship/Wrong.html").forward(request, response);

		}

	}

	public void searchAll(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		ArrayList<PaymentBean> listAll = pDao.searchAll();
		request.getSession(true).setAttribute("paymentRead", listAll);
		request.getRequestDispatcher("/Sponsorship/SearchResult.jsp").forward(request, response);
	}
}
