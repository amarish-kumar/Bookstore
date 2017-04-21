package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BookDAO;
import DAO.BookstoreDAOImp;
import bean.Book;
import service.CatalogInfo;


/**
 * Servlet implementation class Main
 */
@WebServlet("/Catalog")
public class Catalog extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookstoreDAOImp bookstore;
	//private static final String CATALOG = "Catalog.jspx";
	private List<Book> bookList;
	private BookDAO bdao;
	//private CatalogInfo CATALOG;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Catalog() {
        super();
    	bookstore = new BookstoreDAOImp();
    	bdao = new BookDAO();
    	//CATALOG = (CatalogInfo) this.getServletContext().getAttribute("CATALOG");
        //BookstoreDAOImp bookstore = new BookstoreDAOImp();
        // TODO Auto-generated constructor stub
    }
   
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml");
		CatalogInfo CATALOG = (CatalogInfo) this.getServletContext().getAttribute("CATALOG");
		String url= this.getServletContext().getContextPath() + "/Start" ;
		String clientUrl = request.getRequestURI();
		String reqtype = request.getParameter("reqtype");
		
		

		
		if(!clientUrl.endsWith("/Start") && reqtype == null)
		{
			response.sendRedirect(url);
			return;
		}
		String query = request.getParameter("query");
		if(query != null){
			if(query.equals("All")){
				bookList = CATALOG.getAllBooks();
			}
			else if(query.equals("search")){
				String text = request.getParameter("text");
				bookList = CATALOG.searchBooks(text);
			}
			else
			{
				//bookList = bookstore.findBookByCatergory(query);
				bookList = CATALOG.getBookByCatergory(query);		
			}
			
			request.getSession().setAttribute("bookList", bookList);	
			RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/responses/Product.jspx");
			rq.forward(request, response);
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
