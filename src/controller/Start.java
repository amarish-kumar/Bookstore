package controller;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BookDAO;
import DAO.BookstoreDAOImp;
import bean.Book;
import model.Calculation;
import service.AdminService;
import service.CatalogInfo;
import service.CustomerInfo;
import service.POInfo;
;

/**
 * Servlet implementation class Start
 */
@WebServlet("/Start/*")
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String MAIN = "Index.jspx";
	private Map<Book,String> checkOutBookList;
	

	private int cost;


    /**
     * @see HttpServlet#HttpServlet()
     */
    public Start() {
        super();
    	
    	//checkOutBookList = new ArrayList<Book>();
    	checkOutBookList = new HashMap<Book,String>();

    }
    
    @Override
    public void init() throws ServletException{
    	super.init();
    	CatalogInfo CATALOG = new CatalogInfo();
    	CustomerInfo CUSTOMERINFO = new CustomerInfo();
    	AdminService ADMINSERVICE = new AdminService();
    	POInfo POINFO = new POInfo();
    	this.getServletContext().setAttribute("ADMINSERVICE",ADMINSERVICE);
		this.getServletContext().setAttribute("CATALOG",CATALOG);
		this.getServletContext().setAttribute("CUSTOMERINFO",CUSTOMERINFO);
		this.getServletContext().setAttribute("POINFO",POINFO);

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml");
		
		String reqtype = request.getParameter("reqtype");
		if(reqtype != null){
			if(reqtype.equals("catalog")){
				request.getRequestDispatcher("/Catalog").forward(request, response);
			}
			else if(reqtype.equals("payment"))
			{
				request.getRequestDispatcher("/Payment").forward(request, response);
				
				//out.write(this.getServletContext().getInitParameter("appName"));
			}
			else if(reqtype.equals("account"))
			{
				request.getRequestDispatcher("/Account").forward(request, response);
				
				//out.write(this.getServletContext().getInitParameter("appName"));
			}
			else if(reqtype.equals("confirm"))
			{
				request.getRequestDispatcher("/Confirm").forward(request, response);
				
				//out.write(this.getServletContext().getInitParameter("appName"));
			}
			else if(reqtype.equals("confirm"))
			{
				request.getRequestDispatcher("/Confirm").forward(request, response);
			}
			else if(reqtype.equals("logout") || reqtype.equals("login"))
			{
				request.getRequestDispatcher("/Login").forward(request, response);
			}
			else if(reqtype.equals("review")){
				request.getRequestDispatcher("/Review").forward(request, response);
			}
			
		}
		else
		{
			request.getRequestDispatcher(MAIN).forward(request, response);

		}
		
		
		//System.out.println(request.getParameter("all"))

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CatalogInfo CATALOG = (CatalogInfo) this.getServletContext().getAttribute("CATALOG");
		String path = request.getContextPath();
		Map<String,String[]> map = request.getParameterMap();
		String reqtype = map.get("reqtype")[0];
		if(reqtype.equals("checkout"))
		{
			try {
				if(!map.isEmpty()){
					Set<String> ks = new HashSet<String>(map.keySet());
					for(String k : ks){
						//fix here
						if(!k.equals("reqtype")){
							Book b = CATALOG.getBookById(k);
							String v =  map.get(k)[0];
							if(v.equals("0")){
								checkOutBookList.remove(b);
							}
							else
							{
								checkOutBookList.put(b,v);
							}
						}
						
					}
					cost = Calculation.calculateCost(checkOutBookList);
					request.getSession().setAttribute("cost",cost);
					request.getSession().setAttribute("checkOutBookList", checkOutBookList);
	
				}
				request.getRequestDispatcher("/Checkout").forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(reqtype.equals("payment")){
			request.getRequestDispatcher("/Payment").forward(request, response);
			//response.sendRedirect(path + "/Payment");
		}
		else if(reqtype.equals("account")){
			request.getRequestDispatcher("/Account").forward(request, response);
			//response.sendRedirect(path + "/Account");
		}
		else if(reqtype.equals("login") || reqtype.equals("logout")){
			request.getRequestDispatcher("/Login").forward(request, response);
			//response.sendRedirect(path + "/Account");
		}
		else if(reqtype.equals("confirm")){
			//request.getRequestDispatcher("/Confirm").forward(request, response);
			response.sendRedirect(path + "/Confirm");
		}
		else if(reqtype.equals("review")){
			request.getRequestDispatcher("/Review").forward(request, response);
		}
		
		
	}

}
