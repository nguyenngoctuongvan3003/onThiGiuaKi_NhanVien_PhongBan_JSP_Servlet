package onThiGK.controllers;

import jakarta.persistence.EntityManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import onThiGK.dao.NhanVienDAO;
import onThiGK.dao.PhongBanDAO;
import onThiGK.daoImpl.NhanVienDAOImpl;
import onThiGK.daoImpl.PhongBanDAOImpl;
import onThiGK.entities.NhanVien;
import onThiGK.entities.PhongBan;
import onThiGK.utils.EntityManagerFactoryUtils;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Servlet implementation class QuanLiNhanVienServlet
 */

public class QuanLiNhanVienServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EntityManagerFactoryUtils utils;
	private NhanVienDAO nhanVienDAO;
	private PhongBanDAO phongBanDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuanLiNhanVienServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		utils = new EntityManagerFactoryUtils();
		System.out.println("ok");

		nhanVienDAO = new NhanVienDAOImpl(utils.getEntityManager());
		phongBanDAO = new PhongBanDAOImpl(utils.getEntityManager());

	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		utils.close();
		super.destroy();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action= request.getParameter("action")!=null?request.getParameter("action"):"";
		switch (action) {
		case "findByPhongBan":
			displayByPhongBan(request, response);
			break;
		case "showInsert":
			showInsert(request, response);
			break;
		case "showEdit":
			showEdit(request, response);
			break;
		case "delete":
			deleteNhanVien(request, response);
			break;
		
		default:
			displayAll(request, response);
			break;
		}
//		// TODO Auto-generated method stub
//		insertNhanVien(request, response);
//		displayAll(request, response);
//		displayByPhongBan(request, response);
//		deleteNhanVien(request, response);
//		displayAll(request, response);

	}

	protected void displayAll(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		List<NhanVien> listNV = nhanVienDAO.findAll();
		request.setAttribute("listNV", listNV);
		request.setAttribute("phongBan", phongBanDAO.findAll());
		RequestDispatcher dispatcher=request.getRequestDispatcher("views/list.jsp");
		dispatcher.forward(request, response);
	}

	protected void displayByPhongBan(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<NhanVien> listNV = nhanVienDAO.findByPhongBan(phongBanDAO.findById(Integer.parseInt(request.getParameter("id"))));
		request.setAttribute("listNV", listNV);
		RequestDispatcher dispatcher=request.getRequestDispatcher("views/list.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void displayByString(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Received value: " + request.getParameter("value"));
		List<NhanVien> listNV = nhanVienDAO.findTheoNhieuThuocTinh(request.getParameter("value"));
		request.setAttribute("listNV", listNV);

		RequestDispatcher dispatcher=request.getRequestDispatcher("views/list.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void showInsert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher=request.getRequestDispatcher("views/insert.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void showEdit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id= Integer.parseInt(request.getParameter("id"));
		RequestDispatcher dispatcher=request.getRequestDispatcher("views/edit.jsp");
		request.setAttribute("nv", nhanVienDAO.findNVByID(id));
		dispatcher.forward(request, response);
	}

	protected void insertNhanVien(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("submit ok");
		String tenNV= request.getParameter("tenNV");
		String email= request.getParameter("email");
		String diaChi= request.getParameter("diaChi");
		String dienThoai= request.getParameter("dienThoai");
		int id= Integer.parseInt(request.getParameter("maPhongBan")!=null?request.getParameter("maPhongBan"):"-1");
		
		NhanVien nhanVien=new NhanVien(tenNV, email, diaChi, dienThoai, phongBanDAO.findById(id));
		ValidatorFactory validatorFactory= Validation.buildDefaultValidatorFactory();
		Validator validator= validatorFactory.getValidator();
		Set<ConstraintViolation<NhanVien>> violations = validator.validate(nhanVien);
		
		if(violations.isEmpty()) {
			nhanVienDAO.themNV(nhanVien);
			response.sendRedirect("QuanLiNhanVienServlet");
		}else {
			StringBuilder stringBuilder=new StringBuilder();
			violations.forEach(t -> {
				stringBuilder.append(t.getPropertyPath()+": "+t.getMessage());
				stringBuilder.append("<br/>");
			});
			
			request.setAttribute("nv", nhanVien);
			request.setAttribute("loi", stringBuilder);
			RequestDispatcher dispatcher=request.getRequestDispatcher("views/insert.jsp");
			dispatcher.forward(request, response);
			
		}
		
		
		
	}

	protected void updateNhanVien(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int maNV= Integer.parseInt(request.getParameter("maNV"));
		String tenNV= request.getParameter("tenNV");
		String email= request.getParameter("email");
		String diaChi= request.getParameter("diaChi");
		String dienThoai= request.getParameter("dienThoai");
		int id= Integer.parseInt(request.getParameter("maPhongBan")!=null?request.getParameter("maPhongBan"):"-1");
		
		NhanVien nhanVien=new NhanVien(maNV, tenNV, email, diaChi, dienThoai, phongBanDAO.findById(id));
		ValidatorFactory validatorFactory= Validation.buildDefaultValidatorFactory();
		Validator validator= validatorFactory.getValidator();
		Set<ConstraintViolation<NhanVien>> violations = validator.validate(nhanVien);
		
		if(violations.isEmpty()) {
			nhanVienDAO.suaNV(nhanVien);
			response.sendRedirect("QuanLiNhanVienServlet");
		}else {
			StringBuilder stringBuilder=new StringBuilder();
			violations.forEach(t -> {
				stringBuilder.append(t.getPropertyPath()+": "+t.getMessage());
				stringBuilder.append("<br/>");
			});
			
			request.setAttribute("nv", nhanVien);
			request.setAttribute("loi", stringBuilder);
			RequestDispatcher dispatcher=request.getRequestDispatcher("views/edit.jsp");
			dispatcher.forward(request, response);
			
		}
		
		
		
	}

	protected void deleteNhanVien(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		nhanVienDAO.xoaNV(Integer.parseInt(request.getParameter("id")));
		response.sendRedirect("QuanLiNhanVienServlet");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action= request.getParameter("action")!=null?request.getParameter("action"):"";
		switch (action) {
		case "add":
			insertNhanVien(request, response);
			break;
		case "edit":
			updateNhanVien(request, response);
			break;
		case "search":
			displayByString(request, response);
			break;
		default:
			displayAll(request, response);
			break;
		}
	}

}
