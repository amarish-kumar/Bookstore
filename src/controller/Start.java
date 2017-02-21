package controller;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Book;
import model.BookstoreDAOImp;
import model.Calculation;

/**
 * Servlet implementation class Start
 */
@WebServlet("/Start/*")
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String MAIN = "Main.jspx";
	private Map<Book,String> checkOutBookList;
	private BookstoreDAOImp bookstore;
	private int cost;


    /**
     * @see HttpServlet#HttpServlet()
     */
    public Start() {
        super();
    	bookstore = new BookstoreDAOImp();
    	//checkOutBookList = new ArrayList<Book>();
    	checkOutBookList = new HashMap<Book,String>();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String query = request.getParameter("query");
		String payment = request.getParameter("payment");
		if(query != null){
			request.getRequestDispatcher("/Catalog").forward(request, response);
		}
		else if(payment != null)
		{
			request.getRequestDispatcher("/Payment").forward(request, response);
			
			//out.write(this.getServletContext().getInitParameter("appName"));
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

		Map<String,String[]> map = request.getParameterMap();
		String reqtype = map.get("reqtype")[0];
		if(reqtype.equals("checkout"))
		{
			if(!map.isEmpty()){
				Set<String> ks = new HashSet<String>(map.keySet());
				for(String k : ks){
					//fix here
					if(!k.equals("reqtype")){
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
					
				}
				cost = Calculation.calculateCost(checkOutBookList);
				request.getSession().setAttribute("cost",cost);
				request.getSession().setAttribute("checkOutBookList", checkOutBookList);

			}
			request.getRequestDispatcher("/Checkout").forward(request, response);
		}
		else if(reqtype.equals("payment")){
			request.getRequestDispatcher("/Payment").forward(request, response);
		}
		
		//System.out.println(name);
	}

}
