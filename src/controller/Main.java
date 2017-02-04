package controller;

import java.io.IOException;
import java.util.List;

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
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookstoreDAOImp bookstore;
	private static final String MAIN = "Main.jspx";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        //BookstoreDAOImp bookstore = new BookstoreDAOImp();
        // TODO Auto-generated constructor stub
    }
   
    @Override
    public void init() throws ServletException{
    	bookstore = new BookstoreDAOImp();
    	List<Book> bookList = bookstore.findAllBooks();
    	this.getServletContext().setAttribute("bookList", bookList);
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//BookstoreDAOImp bookstore = new BookstoreDAOImp();
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher(MAIN).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
