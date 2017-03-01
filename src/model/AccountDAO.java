package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.AccountModel;
import bean.Address;

public class AccountDAO {

	private DataSource ds;
	public AccountDAO() {
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/PROJECT");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public AccountModel getAccount(String login) {
	 	AccountModel account = new AccountModel();
			try {
				String query =   "SELECT *"
				           + " FROM bookstore.Account"
				           + " WHERE login=\'" 
				           + login 
				           + "\'";
				Connection con = this.ds.getConnection();
				PreparedStatement p = con.prepareStatement(query);
				ResultSet answers = p.executeQuery(); 
				if(answers.next())
				{
			        String log = answers.getString("login");
			        int address = Integer.parseInt(answers.getString("address"));
			        String fname = answers.getString("fname");
			        String lname = answers.getString("lname");
			        String pass = answers.getString("pass");
			        account.setLogin(log);
			        account.setAddress(address);
			        account.setFname(fname);
			        account.setLname(lname);
			        account.setPass(pass);
				}
				answers.close();
		    	p.close();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return account;
	}
	
	public String getPassword(String login) {
	    String pass = null;
		try {
			String query =  "SELECT pass"
			           + " FROM bookstore.Account"
			           + " WHERE login=\'" 
			           + login 
			           + "\'";
			Connection con = this.ds.getConnection();
			PreparedStatement p = con.prepareStatement(query);
			ResultSet answers = p.executeQuery();
			 
			 if(answers.next())
			 {
		         pass = answers.getString(1);
			 }
		
	    	 
	    	 answers.close();
	    	 p.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pass;

	}
	
}
