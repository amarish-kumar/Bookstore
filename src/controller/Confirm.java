package controller;

import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Book;
import bean.CreditCard;
import bean.PO;
import model.BookDAO;
import model.BookstoreDAOImp;
import model.CreditCardDAO;
import model.PODAO;

/**
 * Servlet implementation class Confirm
 */
@WebServlet("/Confirm")
public class Confirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONFIRM = "Confirm.jspx";
	private static final String VERIFIED = "Verified.jspx";

	private BookstoreDAOImp bookstore;
	private CreditCardDAO ccdao;
	private BookDAO bdao;
	private PODAO podao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Confirm() {
        super();
    	bookstore = new BookstoreDAOImp();
    	ccdao = new CreditCardDAO();
    	bdao = new BookDAO();
    	podao = new PODAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher(CONFIRM).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String[]> map = request.getParameterMap();
		int ccinput = Integer.parseInt(map.get("creditcard")[0]);
		CreditCard cc = ccdao.getCreditCard(ccinput);
		Writer out = response.getWriter();
		
		if(cc.getNum() > 0 && cc.getNum() == ccinput)
		{
			PO po = (PO) request.getSession().getAttribute("po");
			po.setStatus("PROCESSED");
			@SuppressWarnings("unchecked")
			Map<Book,String> booklist = (Map<Book,String>) request.getSession().getAttribute("checkOutBookList");
			Set<Book> ks = new HashSet<Book>(booklist.keySet());
			for (Book b: ks){
				Integer quan = Integer.parseInt(booklist.get(b));
				bdao.updateBook(b,quan);
			}
				//update database
			podao.updatePO(po);
			request.getSession().setAttribute("po",po);
			request.getRequestDispatcher(VERIFIED).forward(request, response);

		}
		else{
			//out.write("invalid creditcard");
			request.getRequestDispatcher(CONFIRM).forward(request, response);
		}
		
	}

}
