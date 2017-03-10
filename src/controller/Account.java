package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AccountModel;
import bean.Address;
import model.AccountDAO;
import model.AddressDAO;
import model.BookstoreDAOImp;

/**
 * Servlet implementation class Account
 */
@WebServlet("/Account")
public class Account extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ACCOUNT = "Account.jspx";
	private BookstoreDAOImp bookstore;
	private AddressDAO adao;
	private AccountDAO accdao;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Account() {
        super();
        // TODO Auto-generated constructor stub
    	bookstore = new BookstoreDAOImp();
    	adao = new AddressDAO();
    	accdao = new AccountDAO();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher(ACCOUNT).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		Map<String,String[]> map = request.getParameterMap();
		String login = map.get("login")[0];
		String fname = map.get("fname")[0];
		String lname = map.get("lname")[0];
		String pass = map.get("password")[0];
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
		//bookstore.insertAddress(address);
		adao.insertAddress(address);
		//int id = bookstore.getAddressId(street, province, country, zip, phone);
		Integer id = adao.getAddressId(street, province, country, zip, phone);
		address.setId(id);
		AccountModel account = new AccountModel();
		account.setLogin(login);
		account.setAddress(id);
		account.setFname(fname);
		account.setLname(lname);
		account.setPass(pass);
		accdao.insertAccount(account);
		
	}

}
