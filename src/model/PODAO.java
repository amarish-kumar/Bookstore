package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.PO;

public class PODAO {

	private DataSource ds;
	public PODAO() {
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/PROJECT");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public Integer getPOId(String lname, String fname, String status, int address) {
		Integer id = null;
		try {
			String query =    "SELECT id"
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
			Connection con = this.ds.getConnection();
			PreparedStatement p = con.prepareStatement(query);
			ResultSet answers = p.executeQuery();
			if(answers.last()){
				 String res = answers.getString(1);
			     id = Integer.parseInt(res);
			}
			answers.close();
	    	p.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	public void insertPO(PO po) {
		try {
			String  updateText =
					 "INSERT INTO PO (lname, fname, status, address)"
					           + " VALUE (" 
					           + "\'" + po.getLname() + "\',"
					    	   + "\'" + po.getFname() + "\',"
					    	   + "\'" + po.getStatus() + "\',"
					    	   + po.getAddress() + ")";  
			Connection con = this.ds.getConnection();
			PreparedStatement p = con.prepareStatement(updateText);
			p.executeUpdate();
			p.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	}
	

}
