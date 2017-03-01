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

import bean.Book;
import bean.POItem;
import model.BookstoreDAOImp;
import model.Calculation;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CHECKOUT = "Checkout.jspx";
	//private List<Book> checkOutBookList;
//	private Map<Book,String> checkOutBookList;
//	private BookstoreDAOImp bookstore;
//	private int cost;
	private List<POItem> poi;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Checkout() {
        super();
//    	bookstore = new BookstoreDAOImp();
//    	checkOutBookList = new HashMap<Book,String>();
        poi = new ArrayList<POItem>();
    	

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
	/*
		Map<String,String[]> map = request.getParameterMap();
		
		if(!map.isEmpty()){
			Set<String> ks = new HashSet<String>(map.keySet());
			for(String k : ks){
				//fix here
				
				Book b = bookstore.findBookById(k);
				String v =  map.get(k)[0];
				if(v.equals("0")){
					checkOutBookList.remove(b);
				}
				else
				{
					checkOutBookList.put(b,v);
				}
			}
			cost = Calculation.calculateCost(checkOutBookList);
			request.getSession().setAttribute("cost",cost);
			request.getSession().setAttribute("checkOutBookList", checkOutBookList);

		}*/
//		@SuppressWarnings("unchecked")
//		Map<Book,String> map = (Map<Book, String>) request.getSession().getAttribute("checkOutBookList");
//		Set<Book> ks = map.keySet();
//		for(Book b : ks){
//			POItem item = new POItem();
//			item.setBid(b.getBid());
//			
//		}
		request.getRequestDispatcher(CHECKOUT).forward(request, response);
	
	}

}
