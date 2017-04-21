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

import bean.Book;

public class AdminDAO {


	private DataSource ds;
	public AdminDAO() {
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/PROJECT");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public Book getMostPopularBook(){
		Book book = new Book();

	    try {

			String query = "select t.bid, t.title, t.category, t.price, max(number_sold) as most_sold"
					     + " from (select b.bid, b.title, b.category, b.price, sum(poi.quan) as number_sold"
						 + "       from PO p, POItem poi, Book b"
						 + "       where p.id = poi.id and"
						 + "		     poi.bid = b.bid"
						 + "             group by b.bid, b.title, b.category, b.price) t";
			Connection con = this.ds.getConnection();
			PreparedStatement p = con.prepareStatement(query);
			ResultSet answers = p.executeQuery();
			if(answers.next()){
				String bid = answers.getString("bid");
				String title = answers.getString("title");
				String category = answers.getString("category");
				Integer price = Integer.parseInt(answers.getString("price"));
				Integer sold = Integer.parseInt(answers.getString("most_sold"));
				book.setBid(bid);
				book.setTitle(title);
				book.setCatergory(category);
				book.setPrice(price);
				book.setQuan(sold);
			}
			answers.close();
	    	p.close();
	    	con.close();

		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
	    
		return book;
	}
	
	public Map<String,Integer> getUserSpents(){
		Map<String,Integer> m = new HashMap<String,Integer>();

	    try {

			String query = "select acc.login, sum(poi.price) as amount_spent"
					     + " from PO p, Account acc, POItem poi"
						 + " where p.id = poi.id and"
						 + " p.address = acc.address"
						 + " group by acc.login";
						
			Connection con = this.ds.getConnection();
			PreparedStatement p = con.prepareStatement(query);
			ResultSet answers = p.executeQuery();
			while(answers.next()){
				String user = answers.getString("login");
				Integer amt = Integer.parseInt(answers.getString("amount_spent"));
				m.put(user,amt);
			}
			answers.close();
	    	p.close();
	    	con.close();

		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
	    
		return m;
	}
	
	public List<Book> getBookSoldByMonth(int m){
		List<Book> books = new ArrayList<Book>();

	    try {

			String query =  "SELECT b.bid, b.title, b.category, b.price, poi.quan"
			           + " FROM PO p, POItem poi, Book b"
			           + " WHERE p.month=" 
			           + m 
			           + " and p.id = poi.id"
			           + " and poi.bid = b.bid";
			Connection con = this.ds.getConnection();
			PreparedStatement p = con.prepareStatement(query);
			ResultSet answers = p.executeQuery();
			while (answers.next()) {
				 Book book = new Book();
			     String bid = answers.getString("bid");
			     String title = answers.getString("title");
			     String price = answers.getString("price");
			     String catergory = answers.getString("category");
			     int quan = Integer.parseInt(answers.getString("quan"));
			     book.setBid(bid);
			     book.setTitle(title);
			     book.setPrice(Integer.parseInt(price));
			     book.setCatergory(catergory);
			     book.setQuan(quan);
			     books.add(book);
			 	
			     
			 }
			answers.close();
	    	p.close();
	    	con.close();

		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
	    
		return books;
	}
}
