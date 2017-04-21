package controller;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BookstoreDAOImp;
import bean.Book;
import bean.POItem;
import model.Calculation;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CHECKOUT = "/WEB-INF/responses/Checkout.jspx";
	//private List<Book> checkOutBookList;
//	private Map<Book,String> checkOutBookList;
//	private BookstoreDAOImp bookstore;
//	private int cost;
	//private List<POItem> poi;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Checkout() {
        super();
//    	bookstore = new BookstoreDAOImp();
//    	checkOutBookList = new HashMap<Book,String>();
        //poi = new ArrayList<POItem>();
    	

        // TODO Auto-generated constructor stub
    }
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml");
		String url= this.getServletContext().getContextPath() + "/Start" ;
		String clientUrl = request.getRequestURI();
		String reqtype = request.getParameter("reqtype");
		
		if(!clientUrl.endsWith("/Start") && reqtype == null)
		{
			response.sendRedirect(url);
			return;
		}

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml");
		request.getRequestDispatcher(CHECKOUT).forward(request, response);
	
	}

}
