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

import DAO.AccountDAO;
import DAO.BookstoreDAOImp;
import bean.AccountModel;
import bean.Address;
import bean.Book;
import bean.PO;
import bean.POItem;
import model.Calculation;
import model.Encryption;
import service.CustomerInfo;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookstoreDAOImp bookstore;
	private static final String LOGIN = "/WEB-INF/responses/User.jspx";
	private AccountDAO accdao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    	bookstore = new BookstoreDAOImp();
    	accdao = new AccountDAO();

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
		//String reqtype = request.getParameter("reqtype");
		if(reqtype.equals("logout")){
			request.getSession().setAttribute("login",Boolean.FALSE);
		}
		request.getRequestDispatcher(LOGIN).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml");
		CustomerInfo CUSTOMERINFO = (CustomerInfo) this.getServletContext().getAttribute("CUSTOMERINFO");
		Map<String,String[]> map = request.getParameterMap();
		String login = map.get("login")[0];
		String password = map.get("password")[0];
		String encryptedPass = Encryption.encrypt(password);
		//String pass = accdao.getPassword(login);
		String pass = CUSTOMERINFO.getPassword(login);
		Writer out = response.getWriter();
		//out.write(pass + " " + password);
		if(pass != null){
			if(pass.equals(encryptedPass))
			{
				//AccountModel account = accdao.getAccount(login);
				AccountModel account = CUSTOMERINFO.getAccount(login);
				request.getSession().setAttribute("account",account);
				request.getSession().setAttribute("login",Boolean.TRUE);
				request.getRequestDispatcher(LOGIN).forward(request, response);

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
	}

}
