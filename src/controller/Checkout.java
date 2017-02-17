package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Book;
import model.BookstoreDAOImp;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CHECKOUT = "Checkout.jspx";
	//private List<Book> checkOutBookList;
	private Map<Book,String> checkOutBookList;
	private BookstoreDAOImp bookstore;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Checkout() {
        super();
    	bookstore = new BookstoreDAOImp();
    	//checkOutBookList = new ArrayList<Book>();
    	checkOutBookList = new HashMap<Book,String>();

        // TODO Auto-generated constructor stub
    }
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		Map<String,String[]> map = request.getParameterMap();
		if(map.isEmpty()){
			request.getRequestDispatcher(CHECKOUT).forward(request, response);

		}
		else{
			Set<String> ks = map.keySet();
			for(String k : ks){
				//checkOutBookList.add(bookstore.findBookById(k));
				checkOutBookList.put(bookstore.findBookById(k), map.get(k)[0]);
			}
			request.getSession().setAttribute("checkOutBookList", checkOutBookList);	
			request.getRequestDispatcher(CHECKOUT).forward(request, response);

			

		}
	
	}

}
