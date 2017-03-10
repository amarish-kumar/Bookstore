package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.AccountModel;
import bean.CreditCard;

public class CreditCardDAO {

	private DataSource ds;
	public CreditCardDAO() {
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/PROJECT");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public CreditCard getCreditCard(int num) {
	 	CreditCard creditcard = new CreditCard();
			try {
				String query = "SELECT *"
				           + " FROM Creditcard"
				           + " WHERE num=" 
				           + num;
				Connection con = this.ds.getConnection();
				PreparedStatement p = con.prepareStatement(query);
				ResultSet answers = p.executeQuery(); 
				 if(answers.next())
				 {
			         int num1 = Integer.parseInt(answers.getString(1));
			         String fname = answers.getString(2);
			         String lname = answers.getString(3);
			         creditcard.setNum(num1);
			         creditcard.setFname(fname);
			         creditcard.setLname(lname);
			         
				 }
				answers.close();
		    	p.close();
		    	con.close();

			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return creditcard;
	}
	
}
