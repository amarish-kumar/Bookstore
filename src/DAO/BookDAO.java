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
import bean.PO;



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
	    	con.close();

		
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
    	con.close();


		return book;
		
	}
	
	public List<Book> getBookByCatergory(String c) {
		 List<Book> books = new ArrayList<Book>();

		    try {

				String query =  "SELECT *"
				           + " FROM Book"
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
		    	con.close();

			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	 
		    
			return books;	
	
	}
	
	public void updateBook(Book b, Integer quan) {
		try {
			Integer newquan = b.getQuan() - quan;
			PreparedStatement p = null;
			Connection con = this.ds.getConnection();
			String  updateText = null;
			
			if(newquan > 0){
				updateText = "UPDATE BOOK"
						   + " SET quan="
				 		   +  newquan
				           + " WHERE bid=" 
				           + "\'" + b.getBid() + "\'";
			}
			else{
				updateText = "DELETE FROM BOOK"
				           + " WHERE bid=" 
				           + "\'" + b.getBid() + "\'";
			}
			p = con.prepareStatement(updateText);
			p.executeUpdate();
			p.close();
	    	con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
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
	
	
	public List<Book> getBookByTitle(String t){

		List<Book> books = new ArrayList<Book>();

	    try {

			String query =  "select *"
				     + " from book"
					 + " where title="
				     + "'" + t + "'";
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
	
	

	public List<Book> searchBooks(String t) {
		 List<Book> books = new ArrayList<Book>();

		    try {

				String query = "SELECT * FROM BookSearch WHERE title MATCH '*" + t +"*'"
				           + " union"
				           + " SELECT * FROM BookSearch WHERE category MATCH '*" + t +"*'" 
				           + " union"
				           + " SELECT * FROM BookSearch WHERE bid MATCH '*" + t +"*'";
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
