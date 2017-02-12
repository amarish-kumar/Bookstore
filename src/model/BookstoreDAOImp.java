package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookstoreDAOImp implements BookstoreDAO {

	private Connection conDB;   // Connection to the database system.
	private String url;
	
	public BookstoreDAOImp(){
		try {
			//Class.forName returns a object Class of the specified string
			//in another word a object of class CLass
			// This will Register the driver with DriverManager.
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		 url = "jdbc:mysql://localhost/bookstore?" +
               "user=root&password=diehard";
		 
		 try {
			//this is equivalent to calling db2 connect to eecs3421 in terminal
			 conDB = DriverManager.getConnection(url);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
	}
	@Override
	public List<Book> findAllBooks() {
		String            queryText = "";     // The SQL text.
	    PreparedStatement querySt   = null;   // The query handle.
	    ResultSet         answers   = null;   // A cursor.
	    queryText =
	             "SELECT *"
	           + " FROM bookstore.Book";
	    List<Book> books = new ArrayList<Book>();
	    
	    try {
			querySt = conDB.prepareStatement(queryText);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    try {
			answers = querySt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    try {
	    	 while (answers.next()) {
	    		 Book book = new Book();
	             String bid = answers.getString(1);
	             String title = answers.getString(2);
	             String price = answers.getString(3);
	             String catergory = answers.getString(4);
	             book.setBid(bid);
	             book.setTitle(title);
	             book.setPrice(Integer.parseInt(price));
	             book.setCatergory(catergory);
	             books.add(book);
	             //System.out.println(p + " " + p1 + " " + p2);
	             
	             
	         }
	    	 
	    	 answers.close();
	    	 querySt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}

	@Override
	public void insert(Book book) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Book book) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String bid) {
		// TODO Auto-generated method stub

	}
	@Override
	public List<Book> findBookByCatergory(String c) {
		String            queryText = "";     // The SQL text.
	    PreparedStatement querySt   = null;   // The query handle.
	    ResultSet         answers   = null;   // A cursor.
	    queryText =
	             "SELECT *"
	           + " FROM bookstore.Book"
	           + " WHERE category=\'" 
	           + c 
	           + "\'";
	    List<Book> books = new ArrayList<Book>();
	    
	    try {
			querySt = conDB.prepareStatement(queryText);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    try {
			answers = querySt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    try {
	    	 while (answers.next()) {
	    		 Book book = new Book();
	             String bid = answers.getString(1);
	             String title = answers.getString(2);
	             String price = answers.getString(3);
	             String catergory = answers.getString(4);
	             book.setBid(bid);
	             book.setTitle(title);
	             book.setPrice(Integer.parseInt(price));
	             book.setCatergory(catergory);
	             books.add(book);
	             //System.out.println(p + " " + p1 + " " + p2);
	             
	             
	         }
	    	 
	    	 answers.close();
	    	 querySt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}

}
