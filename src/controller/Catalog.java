package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Book;
import model.BookstoreDAOImp;


/**
 * Servlet implementation class Main
 */
@WebServlet("/Catalog")
public class Catalog extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookstoreDAOImp bookstore;
	private static final String CATALOG = "Catalog.jspx";
	private List<Book> bookList;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Catalog() {
        super();
        //BookstoreDAOImp bookstore = new BookstoreDAOImp();
        // TODO Auto-generated constructor stub
    }
   
    @Override
    public void init() throws ServletException{
    	bookstore = new BookstoreDAOImp();
    	
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//BookstoreDAOImp bookstore = new BookstoreDAOImp();
		//response.getWriter().append("Served at: ").append(request.getContextPath())
		String query = request.getParameter("query");
		if(query.equals("All")){
			bookList = bookstore.findAllBooks();
		}
		else
		{
			bookList = bookstore.findBookByCatergory(query);
		}
		
		request.setAttribute("bookList", bookList);	
		RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/xml/products.jspx");
		rq.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
