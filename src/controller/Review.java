package controller;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ReviewDAO;
import bean.ReviewBean;
import service.CatalogInfo;


/**
 * Servlet implementation class Review
 */
@WebServlet("/Review")
public class Review extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Review() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml");
		CatalogInfo CATALOG = (CatalogInfo) this.getServletContext().getAttribute("CATALOG");
		String url= this.getServletContext().getContextPath() + "/Start" ;
		String clientUrl = request.getRequestURI();
		String reqtype = request.getParameter("reqtype");
		
		if(!clientUrl.endsWith("/Start") && reqtype == null)
		{
			response.sendRedirect(url);
			return;
		}
		//Boolean login = (Boolean) request.getSession().getAttribute("login");
		String bid = request.getParameter("bid");
		if(bid != null){
			List<ReviewBean> reviewList = CATALOG.getReviewsByBid(bid);
			Double average_rating = CATALOG.getAverageRatingByBid(bid);

			request.getSession().setAttribute("average_rating",average_rating);
			request.getSession().setAttribute("bid",bid);
			request.getSession().setAttribute("reviewList",reviewList);
			request.getRequestDispatcher("/WEB-INF/responses/Review.jspx").forward(request,response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml");
		CatalogInfo CATALOG = (CatalogInfo) this.getServletContext().getAttribute("CATALOG");
		String bid = request.getParameter("bid");
		String login = request.getParameter("login");
		String rev = request.getParameter("rev");
		String rating = request.getParameter("rating");
		CATALOG.insertReview(bid,login,rev,rating);
		Double average_rating = CATALOG.getAverageRatingByBid(bid);
		List<ReviewBean> reviewList = CATALOG.getReviewsByBid(bid);
		request.getSession().setAttribute("average_rating",average_rating);
		request.getSession().setAttribute("reviewList",reviewList);
		request.getRequestDispatcher("/WEB-INF/responses/Review.jspx").forward(request,response);




	}

}
