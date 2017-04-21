package controller;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BookDAO;
import DAO.PODAO;
import bean.Book;
import bean.PO;
import service.AdminService;
import service.CatalogInfo;
import service.CustomerInfo;
import service.POInfo;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin/*")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADMIN = "Admin.jspx";
	//private BookDAO bdao;
 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
        // TODO Auto-generated constructor stub
        //bdao = new BookDAO();
    }
    
    
    @Override
    public void init() throws ServletException{
    	super.init();
    	AdminService ADMINSERVICE = new AdminService();
		this.getServletContext().setAttribute("ADMINSERVICE",ADMINSERVICE);
		

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			response.setContentType("text/xml");
			AdminService ADMINSERVICE = (AdminService) this.getServletContext().getAttribute("ADMINSERVICE");
			String s = request.getParameter("s");
			Writer out = response.getWriter();
			if(s != null)
			{
				//request.getRequestDispatcher(ADMIN).forward(request, response);
				Integer m = Integer.parseInt(s);
				if(m >= 0 && m <= 12){
					List<Book> monthList = ADMINSERVICE.getBookSoldByMonth(m);
					request.getSession().setAttribute("monthList",monthList);
					request.getRequestDispatcher("/WEB-INF/responses/Report.jspx").forward(request,response);

				}
				else if(m == 13){
					Book popular = ADMINSERVICE.getMostPopularBook();
					request.getServletContext().setAttribute("popular",popular);
					request.getRequestDispatcher("/WEB-INF/responses/Popular.jspx").forward(request,response);
				}
//				else if(m == 14){
//					request.getRequestDispatcher("/WEB-INF/responses/AnomizedReport.jspx").forward(request,response);
//				}
				else
				{
					out.write("invalid selection");
				}
			}
			else{
				request.getRequestDispatcher(ADMIN).forward(request, response);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
