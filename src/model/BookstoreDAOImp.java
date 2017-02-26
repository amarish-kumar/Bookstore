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
	             int quan = Integer.parseInt(answers.getString(5));
	             book.setBid(bid);
	             book.setTitle(title);
	             book.setPrice(Integer.parseInt(price));
	             book.setCatergory(catergory);
	             book.setQuan(quan);
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
	             int quan = Integer.parseInt(answers.getString(5));
	             book.setBid(bid);
	             book.setTitle(title);
	             book.setPrice(Integer.parseInt(price));
	             book.setCatergory(catergory);
	             book.setQuan(quan);
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
	public Book findBookById(String bid) {
		String            queryText = "";     // The SQL text.
	    PreparedStatement querySt   = null;   // The query handle.
	    ResultSet         answers   = null;   // A cursor.
	    queryText =
	             "SELECT *"
	           + " FROM bookstore.Book"
	           + " WHERE bid=\'" 
	           + bid 
	           + "\'";
	   	 Book book = new Book();
	    
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
	    	 answers.next();
	         String bid1 = answers.getString(1);
	         String title = answers.getString(2);
	         String price = answers.getString(3);
	         String catergory = answers.getString(4);
             int quan = Integer.parseInt(answers.getString(5));

	         book.setBid(bid1);
	         book.setTitle(title);
	         book.setPrice(Integer.parseInt(price));
	         book.setCatergory(catergory);
	         book.setQuan(quan);
	    	 
	    	 answers.close();
	    	 querySt.close();
	    

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    	
	    return book;
	}
	@Override
	public void insertAddress(Address add) {
		String            updateText = "";     // The SQL text.
	    PreparedStatement updateSt   = null;   // The query handle.
	    int answer = 0;
	    updateText =
	             "INSERT INTO Address (street, province, country, zip, phone)"
	           + " VALUE (" 
	           + "\'" + add.getStreet() + "\',"
	           + "\'" + add.getProvince() + "\',"
	           + "\'" + add.getCountry() + "\',"
	           + "\'" + add.getZip() + "\',"
	           + "\'" + add.getPhone() + "\')";
	            		 
	          
	    
	    try {
	    	updateSt = conDB.prepareStatement(updateText);
			answer = updateSt.executeUpdate();
			updateSt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
		
	}
	@Override
	public void insertAccount(AccountModel acc) {
		String            updateText = "";     // The SQL text.
	    PreparedStatement updateSt   = null;   // The query handle.
	    int answer = 0;
	    updateText =
	             "INSERT INTO Account (address, login, fname, lname, pass)"
	           + " VALUE (" 
	           + acc.getAddress() + ","
	           + "\'" + acc.getLogin() + "\',"
	    	   + "\'" + acc.getFname() + "\',"
	    	   + "\'" + acc.getLname() + "\',"
	    	   + "\'" + acc.getPass() + "\')";      		 
	    try {
			updateSt = conDB.prepareStatement(updateText);
			answer = updateSt.executeUpdate();
			updateSt.close();

	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	    
		
	}
	@Override
	public int getAddressId(String street, String province, String country, String zip, String phone) {
		String            queryText = "";     // The SQL text.
	    PreparedStatement querySt   = null;   // The query handle.
	    ResultSet         answers   = null;   // A cursor.
	    queryText =
	             "SELECT id"
	           + " FROM bookstore.Address"
	           + " WHERE street=\'" 
	           + street 
	           + "\'"
	           + " and province=\'" 
	           + province 
	           + "\'"
	           + " and country=\'" 
	           + country 
	           + "\'"
	           + " and zip=\'" 
	           + zip
	           + "\'"
	            + " and phone=\'" 
	           + phone
	           + "\'";
	   	 int id = 0;
	    
	    try {
			querySt = conDB.prepareStatement(queryText);
			answers = querySt.executeQuery();
			answers.next();
	        String res = answers.getString(1);
	        id = Integer.parseInt(res);
	    	 
	    	 answers.close();
	    	 querySt.close();
	    

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		return id;
	}
	@Override
	public String getPassword(String login) {
		String            queryText = "";     // The SQL text.
	    PreparedStatement querySt   = null;   // The query handle.
	    ResultSet         answers   = null;   // A cursor.
	    queryText =
	             "SELECT pass"
	           + " FROM bookstore.Account"
	           + " WHERE login=\'" 
	           + login 
	           + "\'";
	    String pass = null;
	    try {
			 querySt = conDB.prepareStatement(queryText);
			 answers = querySt.executeQuery();
			 
			 if(answers.next())
			 {
		         pass = answers.getString(1);
			 }
		
	    	 
	    	 answers.close();
	    	 querySt.close();
	    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    	
		return pass;
	}
	@Override
	public void insertPO(PO po) {
		String            updateText = "";     // The SQL text.
	    PreparedStatement updateSt   = null;   // The query handle.
	    int answer = 0;
	    updateText =
	             "INSERT INTO PO (lname, fname, status, address)"
	           + " VALUE (" 
	           + "\'" + po.getLname() + "\',"
	    	   + "\'" + po.getFname() + "\',"
	    	   + "\'" + po.getStatus() + "\',"
	    	   + po.getAddress() + ")";      		 
	    try {
			updateSt = conDB.prepareStatement(updateText);
			answer = updateSt.executeUpdate();
			updateSt.close();

	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	}
	@Override
	public int getAccountAddress(String login) {
		String            queryText = "";     // The SQL text.
	    PreparedStatement querySt   = null;   // The query handle.
	    ResultSet         answers   = null;   // A cursor.
	    queryText =
	             "SELECT address"
	           + " FROM bookstore.Account"
	           + " WHERE login=\'" 
	           + login 
	           + "\'";
	    int address = 0;
	    try {
			 querySt = conDB.prepareStatement(queryText);
			 answers = querySt.executeQuery();
			 
			 if(answers.next())
			 {
		         address = Integer.parseInt(answers.getString(1));
			 }
		
	    	 
	    	 answers.close();
	    	 querySt.close();
	    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    	
		return address;
	}
	@Override
	public String getLname(String login) {
		String            queryText = "";     // The SQL text.
	    PreparedStatement querySt   = null;   // The query handle.
	    ResultSet         answers   = null;   // A cursor.
	    queryText =
	             "SELECT lname"
	           + " FROM bookstore.Account"
	           + " WHERE login=\'" 
	           + login 
	           + "\'";
	    String Lname = null;
	    try {
			 querySt = conDB.prepareStatement(queryText);
			 answers = querySt.executeQuery();
			 
			 if(answers.next())
			 {
		         Lname = answers.getString(1);
			 }
		
	    	 
	    	 answers.close();
	    	 querySt.close();
	    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    	
		return Lname;
	}
	@Override
	public String getFname(String login) {
		String            queryText = "";     // The SQL text.
	    PreparedStatement querySt   = null;   // The query handle.
	    ResultSet         answers   = null;   // A cursor.
	    queryText =
	             "SELECT fname"
	           + " FROM bookstore.Account"
	           + " WHERE login=\'" 
	           + login 
	           + "\'";
	    String fname = null;
	    try {
			 querySt = conDB.prepareStatement(queryText);
			 answers = querySt.executeQuery();
			 
			 if(answers.next())
			 {
		         fname = answers.getString(1);
			 }
		
	    	 
	    	 answers.close();
	    	 querySt.close();
	    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    	
		return fname;
	}
	@Override
	public int getPOId(String lname, String fname, String status, int address) {
		String            queryText = "";     // The SQL text.
	    PreparedStatement querySt   = null;   // The query handle.
	    ResultSet         answers   = null;   // A cursor.
	    queryText =
	             "SELECT id"
	           + " FROM bookstore.PO"
	           + " WHERE lname=\'" 
	           + lname 
	           + "\'"
	           + " and fname=\'" 
	           + fname 
	           + "\'"
	           + " and status=\'" 
	           + status 
	           + "\'"
	           + " and address=" 
	           + address;
	          
	   	 int id = 0;
	    
	    try {
			querySt = conDB.prepareStatement(queryText);
			answers = querySt.executeQuery();
			if(answers.last()){
				 String res = answers.getString(1);
			     id = Integer.parseInt(res);
			}	 
	    	 answers.close();
	    	 querySt.close();
	    

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		return id;
	}
	@Override
	public void insertPOItem(POItem poi) {
		String            updateText = "";     // The SQL text.
	    PreparedStatement updateSt   = null;   // The query handle.
	    int answer = 0;
	    updateText =
	             "INSERT INTO POItem (id, bid, quan, price)"
	           + " VALUE (" 
	           + poi.getId() + ","
	    	   + "\'" + poi.getBid() + "\',"
	    	   + poi.getQuan() + ","
	    	   + poi.getPrice() + ")";      		 
	    try {
			updateSt = conDB.prepareStatement(updateText);
			answer = updateSt.executeUpdate();
			updateSt.close();

	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	}
	@Override
	public Address getAddress(int id) {
		String            queryText = "";     // The SQL text.
	    PreparedStatement querySt   = null;   // The query handle.
	    ResultSet         answers   = null;   // A cursor.
	    queryText =
	             "SELECT *"
	           + " FROM bookstore.Address"
	           + " WHERE id="
	           + id;
	   	 Address address = new Address();
	    
	    try {
			 querySt = conDB.prepareStatement(queryText);
			 answers = querySt.executeQuery();
			 answers.next();
	         int addid = Integer.parseInt(answers.getString(1));
	         String street = answers.getString(2);
	         String province = answers.getString(3);
	         String country = answers.getString(4);
	         String zip = answers.getString(5);
	         String phone = answers.getString(6);

	         address.setId(addid);
	         address.setStreet(street);
	         address.setProvince(province);
	         address.setCountry(country);
	         address.setZip(zip);
	         address.setPhone(phone);
	    	 
	    	 answers.close();
	    	 querySt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	   
	    	
	    return address;
	}

}
