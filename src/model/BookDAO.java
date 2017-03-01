package model;

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



public class BookDAO {

	private DataSource ds;
	public BookDAO() {
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/PROJECT");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public List<Book> getAllBooks(){
		
	    List<Book> books = new ArrayList<Book>();

	    try {

			String query = "SELECT *"
			           + " FROM Book";
			Connection con = this.ds.getConnection();
			PreparedStatement p = con.prepareStatement(query);
			ResultSet answers = p.executeQuery();
			while (answers.next()) {
				 Book book = new Book();
			     String bid = answers.getString(1);
			     String title = answers.getString(2);
			     String price = answers.getString(3);
			     String catergory = answers.getString(4);
			     int quan = Integer.parseInt(answers.getString(5));
			     book.setBid(bid);
			     book.setTitle(title);
			     book.setPrice(Integer.parseInt(price));
			     book.setCatergory(catergory);
			     book.setQuan(quan);
			     books.add(book);
			 	
			     
			 }
			answers.close();
	    	p.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
	    
		return books;	
	}
	
	public Book getBookById(String bid)  throws SQLException {
		String query = 
				 "SELECT *"
				           + " FROM Book"
				           + " WHERE bid=\'" 
				           + bid 
				           + "\'";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
	   	Book book = new Book();
	    r.next();
        String bid1 = r.getString("bid");
        String title = r.getString("title");
        String price = r.getString("price");
        String catergory = r.getString("category");
        int quan = Integer.parseInt(r.getString("quan"));

        book.setBid(bid1);
        book.setTitle(title);
        book.setPrice(Integer.parseInt(price));
        book.setCatergory(catergory);
        book.setQuan(quan);
   	 
   	 	r.close();
   	 	p.close();

		return book;
		
	}
	
	public List<Book> getBookByCatergory(String c) {
		 List<Book> books = new ArrayList<Book>();

		    try {

				String query =  "SELECT *"
				           + " FROM bookstore.Book"
				           + " WHERE category=\'" 
				           + c 
				           + "\'";
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
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	 
		    
			return books;	
	
	}
}
