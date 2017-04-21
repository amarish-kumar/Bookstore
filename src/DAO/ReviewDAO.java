package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.AccountModel;
import bean.Book;
import bean.ReviewBean;

public class ReviewDAO {
	private DataSource ds;
	public ReviewDAO() {
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/PROJECT");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public List<ReviewBean> getReviewsByBid(String bid){
		List<ReviewBean> list = new ArrayList<ReviewBean>();
		try {
			String query =  "SELECT *"
			           + " FROM Review"
			           + " WHERE bid="
			           + "\'" + bid + "\'";
			          
			Connection con = this.ds.getConnection();
			PreparedStatement p = con.prepareStatement(query);
			ResultSet answers = p.executeQuery();
			
			while(answers.next()){
				ReviewBean review = new ReviewBean();
				Integer rid = Integer.parseInt(answers.getString("rid"));
				String bid1 = answers.getString("bid");
				String login = answers.getString("login");
				String rev = answers.getString("rev");
				Integer rating = Integer.parseInt(answers.getString("rating"));
				review.setRid(rid);
				review.setBid(bid1);
				review.setLogin(login);
				review.setRev(rev);
				review.setRating(rating);
				list.add(review);
			}
			
			answers.close();
	    	p.close();
	    	con.close();

		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
		
	}
	
	public void insertReview( String bid, String login, String rev, String rating) {
	    
	    try {
			String  updateText =
					"INSERT INTO Review (bid, login, rev, rating)"
					           + " VALUES (" 
					           + "\'" + bid + "\',"
					    	   + "\'" + login + "\',"
					    	   + "\'" + rev + "\',"
					    	   + Integer.parseInt(rating) + ")";
					    	   
			Connection con = this.ds.getConnection();
			PreparedStatement p = con.prepareStatement(updateText);
			p.executeUpdate();
			p.close();
	    	con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Double getAverageRatingByBid(String bid){
		Double av_rating = 0.0;

	    try {

			String query = "select avg(rating) as average_rating"
					   		+ " from Review"
					   		+ " where bid="
					   		+ "'" + bid + "'";
			Connection con = this.ds.getConnection();
			PreparedStatement p = con.prepareStatement(query);
			ResultSet answers = p.executeQuery();
			if(answers.next()){
				String avr = answers.getString("average_rating");
				if(avr != null){
					av_rating = Double.parseDouble(answers.getString("average_rating"));
				}
				
			}
			answers.close();
	    	p.close();
	    	con.close();

		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
	    
		return av_rating;
	}

}
