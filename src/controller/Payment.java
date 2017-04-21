package controller;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.AccountDAO;
import DAO.AddressDAO;
import DAO.BookstoreDAOImp;
import DAO.PODAO;
import DAO.POItemDAO;
import bean.AccountModel;
import bean.Address;
import bean.Book;
import bean.PO;
import bean.POItem;
import model.Calculation;
import model.Encryption;
import service.CustomerInfo;
import service.POInfo;

/**
 * Servlet implementation class Payment
 */
@WebServlet("/Payment")
public class Payment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String LOGIN = "/WEB-INF/responses/Login.jspx";
	//private static final String ORDERPROCESS = "OrderProcess1.jspx";
	private static final String CONFIRM =  "/WEB-INF/responses/Confirm.jspx";


	private BookstoreDAOImp bookstore;
	private AddressDAO adao;
	private AccountDAO accdao;
	private POItemDAO poidao;
	private PODAO podao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Payment() {
        super();
        // TODO Auto-generated constructor stub
    	bookstore = new BookstoreDAOImp();
    	adao = new AddressDAO();
    	accdao = new AccountDAO();
    	poidao = new POItemDAO();
    	podao = new PODAO();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			response.setContentType("text/xml");
			POInfo POINFO = (POInfo) this.getServletContext().getAttribute("POINFO");
			String url= this.getServletContext().getContextPath() + "/Start" ;
			String clientUrl = request.getRequestURI();
			String reqtype = request.getParameter("reqtype");
			
			if(!clientUrl.endsWith("/Start") && reqtype == null)
			{
				response.sendRedirect(url);
				return;
			}
			
			Boolean login = (Boolean) request.getSession().getAttribute("login");
			if(login.equals(Boolean.FALSE)){
				request.getRequestDispatcher(LOGIN).forward(request, response);

			}
			else{
				Writer out = response.getWriter();
				
				//int address = accdao.getAccountAddress(login);
				AccountModel account = (AccountModel) request.getSession().getAttribute("account");
				List<POItem> poitems = new ArrayList<POItem>();

//				String lname = bookstore.getLname(login);
//				String fname = bookstore.getFname(login);
				String lname = account.getLname();
				String fname = account.getFname();
				PO po = new PO();
				po.setLname(lname);
				po.setMonth(Calendar.MONTH);
				po.setFname(fname);
				po.setAddress(account.getAddress());
				po.setStatus("ORDERED");
				//podao.insertPO(po);
				POINFO.insertPO(po);
				int id = podao.getPOId2(po.getMonth(),lname, fname, po.getStatus(),account.getAddress());
				if(id > 0){
					po.setId(id);
					@SuppressWarnings("unchecked")
					Map<Book,String> booklist = (Map<Book,String>) request.getSession().getAttribute("checkOutBookList");
					Set<Book> ks = new HashSet<Book>(booklist.keySet());
					Address addr = adao.getAddress(account.getAddress());
					for(Book b : ks){
						POItem poi = new POItem();
						int quan = Integer.parseInt(booklist.get(b));
						int price = Calculation.calculateCost(b, quan);
						poi.setId(id);
						poi.setBid(b.getBid());
						poi.setQuan(quan);
						poi.setPrice(price);
						//poidao.insertPOItem(poi);
						POINFO.insertPOItem(poi);
						poitems.add(poi);
						
					}
					request.getSession().setAttribute("account", account);
					request.getSession().setAttribute("address", addr);
					request.getSession().setAttribute("po", po);
					request.getSession().setAttribute("poitems", poitems);
					request.getRequestDispatcher(CONFIRM).forward(request, response);
				}
				else{
					out.write("order not gone through");
				}

			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//request.getRequestDispatcher(LOGIN).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {		
			response.setContentType("text/xml");
			POInfo POINFO = (POInfo) this.getServletContext().getAttribute("POINFO");
			CustomerInfo CUSTOMERINFO = (CustomerInfo) this.getServletContext().getAttribute("CUSTOMERINFO");
			Map<String,String[]> map = request.getParameterMap();
			String login = map.get("login")[0];
			String password = map.get("password")[0];
			String encryptedPass = Encryption.encrypt(password);
			String pass = CUSTOMERINFO.getPassword(login);
			Writer out = response.getWriter();
			//out.write(pass + " " + password);
			Date d = new Date();
			if(pass != null){
				if(pass.equals(encryptedPass))
				{
					int address = accdao.getAccountAddress(login);
					AccountModel account = accdao.getAccount(login);
					List<POItem> poitems = new ArrayList<POItem>();

//					String lname = bookstore.getLname(login);
//					String fname = bookstore.getFname(login);
					String lname = account.getLname();
					String fname = account.getFname();
					PO po = new PO();
					po.setLname(lname);
					po.setMonth(Calendar.MONTH);
					po.setFname(fname);
					po.setAddress(address);
					po.setStatus("ORDERED");
					//podao.insertPO(po);
					POINFO.insertPO(po);
					int id = podao.getPOId2(po.getMonth(),lname, fname, po.getStatus(), address);
					if(id > 0){
						po.setId(id);
						@SuppressWarnings("unchecked")
						Map<Book,String> booklist = (Map<Book,String>) request.getSession().getAttribute("checkOutBookList");
						Set<Book> ks = new HashSet<Book>(booklist.keySet());
						Address addr = adao.getAddress(address);
						for(Book b : ks){
							POItem poi = new POItem();
							int quan = Integer.parseInt(booklist.get(b));
							int price = Calculation.calculateCost(b, quan);
							poi.setId(id);
							poi.setBid(b.getBid());
							poi.setQuan(quan);
							poi.setPrice(price);
							//poidao.insertPOItem(poi);
							POINFO.insertPOItem(poi);
							poitems.add(poi);
							
						}
						request.getSession().setAttribute("login",Boolean.TRUE);
						request.getSession().setAttribute("account", account);
						request.getSession().setAttribute("address", addr);
						request.getSession().setAttribute("po", po);
						request.getSession().setAttribute("poitems", poitems);
						request.getRequestDispatcher(CONFIRM).forward(request, response);
					}
					else{
						out.write("order not gone through");
					}


				}
				else
				{
					out.write("wrong password");
				}
			}
			else
			{
				out.write("create new account");
			}
			out.flush();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

	}

}
