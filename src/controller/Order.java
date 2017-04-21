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

import DAO.BookDAO;
import DAO.BookstoreDAOImp;
import DAO.CreditCardDAO;
import DAO.PODAO;
import bean.Book;
import bean.CreditCard;
import bean.PO;
import service.CatalogInfo;
import service.CustomerInfo;
import service.POInfo;

/**
 * Servlet implementation class Confirm
 */
@WebServlet("/Order")
public class Order extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAYMENT = "/WEB-INF/responses/Payment.jspx";
	//private static final String CONFIRM = "Confirm1.jspx";
	private static final String VERIFIED = "Verified.jspx";

	private BookstoreDAOImp bookstore;
	private CreditCardDAO ccdao;
	private BookDAO bdao;
	private PODAO podao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Order() {
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
		response.setContentType("text/xml");
		String url= this.getServletContext().getContextPath() + "/Start" ;
		String clientUrl = request.getRequestURI();
		String reqtype = request.getParameter("reqtype");
		
		if(!clientUrl.endsWith("/Start") && reqtype == null)
		{
			response.sendRedirect(url);
			return;
		}
		request.getRequestDispatcher(PAYMENT).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/xml");
			POInfo POINFO = (POInfo) this.getServletContext().getAttribute("POINFO");
			CatalogInfo CATALOG = (CatalogInfo) this.getServletContext().getAttribute("CATALOG");
			CustomerInfo CUSTOMERINFO = (CustomerInfo) this.getServletContext().getAttribute("CUSTOMERINFO");
			Map<String,String[]> map = request.getParameterMap();
			int ccinput = Integer.parseInt(map.get("creditcard")[0]);
			CreditCard cc = CUSTOMERINFO.getCreditCard(ccinput);
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
					
					//bdao.updateBook(b,quan);
					CATALOG.updateBook(b, quan);
				}
				//update database
				//podao.updatePO(po);
				POINFO.updatePO(po);
				request.getSession().setAttribute("po",po);
				request.getRequestDispatcher(VERIFIED).forward(request, response);

			}
			else{
				//out.write("invalid creditcard");
				request.getRequestDispatcher(PAYMENT).forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
