package controller;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BookstoreDAOImp;
import model.CreditCard;
import model.PO;

/**
 * Servlet implementation class Confirm
 */
@WebServlet("/Confirm")
public class Confirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONFIRM = "Confirm.jspx";
	private static final String VERIFIED = "Verified.jspx";

	private BookstoreDAOImp bookstore;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Confirm() {
        super();
    	bookstore = new BookstoreDAOImp();

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
		CreditCard cc = bookstore.getCreditCard(ccinput);
		Writer out = response.getWriter();
		
		if(cc.getNum() > 0 && cc.getNum() == ccinput)
		{
			PO po = (PO) request.getSession().getAttribute("po");
			po.setStatus("PROCESSED");
				//update database
			request.getSession().setAttribute("po",po);
			request.getRequestDispatcher(VERIFIED).forward(request, response);

		}
		else{
			out.write("invalid creditcard");	
		}
		
	}

}
