package controller;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Book;
import bean.PO;
import model.BookDAO;
import model.PODAO;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin/*")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADMIN = "Admin.jspx";
	private BookDAO bdao;
 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
        // TODO Auto-generated constructor stub
        bdao = new BookDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String s = request.getParameter("s");
		Writer out = response.getWriter();
		if(s != null)
		{
			//request.getRequestDispatcher(ADMIN).forward(request, response);
			Integer m = Integer.parseInt(s);
			if(m >= 0 && m <= 11){
				List<Book> monthList = bdao.getBookSoldByMonth(m);
				request.getSession().setAttribute("monthList",monthList);
				request.getRequestDispatcher("/WEB-INF/xml/Report.jspx").forward(request,response);;

			}
			else
			{
				out.write("invalid selection");
			}
		}
		else{
			request.getRequestDispatcher(ADMIN).forward(request, response);

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
