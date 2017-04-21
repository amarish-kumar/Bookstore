package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AccountDAO;
import DAO.AddressDAO;
import DAO.BookstoreDAOImp;
import bean.AccountModel;
import bean.Address;
import model.Encryption;
import service.CatalogInfo;
import service.CustomerInfo;

/**
 * Servlet implementation class Account
 */
@WebServlet("/Account")
public class Account extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String jsp; 
	

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Account() {
        super();
       
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
		jsp = "/WEB-INF/responses/Account.jspx";
		request.getRequestDispatcher(jsp).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/xml");
			CustomerInfo CUSTOMERINFO = (CustomerInfo) this.getServletContext().getAttribute("CUSTOMERINFO");
			Map<String,String[]> map = request.getParameterMap();
			String login = map.get("login")[0];
			if(CUSTOMERINFO.getAccount(login) == null){
				String fname = map.get("fname")[0];
				String lname = map.get("lname")[0];
				String pass = map.get("password")[0];
				String encryptedPass = Encryption.encrypt(pass);
				String street = map.get("street")[0];
				String province = map.get("province")[0];
				String country = map.get("country")[0];
				String zip = map.get("zip")[0];
				String phone = map.get("phone")[0];
				Address address = new Address();
				address.setStreet(street);
				address.setProvince(province);
				address.setCountry(country);
				address.setZip(zip);
				address.setPhone(phone);
				CUSTOMERINFO.insertAddress(address);
				Integer id = CUSTOMERINFO.getAddressId(street, province, country, zip, phone);
				address.setId(id);
				AccountModel account = new AccountModel();
				account.setLogin(login);
				account.setAddress(id);
				account.setFname(fname);
				account.setLname(lname);
				account.setPass(encryptedPass);
				CUSTOMERINFO.insertAccount(account);
				jsp = "/WEB-INF/responses/AccountCreated.jspx";
				
			}
			else{
				jsp = "/WEB-INF/responses/AccountExisted.jspx";
			}
			
			request.getRequestDispatcher(jsp).forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
