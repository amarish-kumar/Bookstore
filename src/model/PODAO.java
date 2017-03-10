package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.Book;
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
	
	public Integer getPOId(int month, String lname, String fname, String status, int address) {
		Integer id = null;
		try {
			String query =    "SELECT id"
			           + " FROM PO"
			           + " WHERE month="
			           + month
			           + " and lname=\'"
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
				 String res = answers.getString("id");
			     id = Integer.parseInt(res);
			}
			answers.close();
	    	p.close();
	    	con.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	public Integer getPOId2(int month, String lname, String fname, String status, int address) {
		Integer id = null;
		try {
			String query =    "SELECT id"
			           + " FROM PO"
			           + " WHERE month="
			           + month
			           + " and lname=\'"
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
			while(answers.next()){
				String res = answers.getString("id");
			    id = Integer.parseInt(res);
			}
			
			answers.close();
	    	p.close();
	    	con.close();

		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	
	public void insertPO(PO po) {
		try {
			String  updateText =
					 "INSERT INTO PO (month, lname, fname, status, address)"
					           + " VALUES ("
					           +  po.getMonth() + ","
					           + "\'" + po.getLname() + "\',"
					    	   + "\'" + po.getFname() + "\',"
					    	   + "\'" + po.getStatus() + "\',"
					    	   + po.getAddress() + ")";  
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
	
	public void updatePO(PO po) {
		try {
			PreparedStatement p = null;
			Connection con = this.ds.getConnection();
			String  updateText = "UPDATE PO"
					   + " SET status="
					   + "\'" +  po.getStatus() + "\'"
			           + " WHERE id=" 
			           +  po.getId();
			
			
			p = con.prepareStatement(updateText);
			p.executeUpdate();
			p.close();
	    	con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	}
	
	public List<PO> getPOByMonth(int m){
		List<PO> list = new ArrayList<PO>();
		try {
			String query =    "SELECT *"
			           + " FROM PO"
			           + " WHERE month="
			           + m
			           + " and status=\'PROCESSED\'";
			           
						
			Connection con = this.ds.getConnection();
			PreparedStatement p = con.prepareStatement(query);
			ResultSet answers = p.executeQuery();
			while (answers.next()) {
				 PO po = new PO();
			     Integer id = Integer.parseInt(answers.getString("id"));
			     String lname = answers.getString("lname");
			     String fname = answers.getString("fname");
			     String status = answers.getString("status");
			     Integer address = Integer.parseInt(answers.getString("address"));
			     po.setId(id);
			     po.setLname(lname);
			     po.setFname(fname);
			     po.setStatus(status);
			     po.setAddress(address);
			     list.add(po);
			 	
			     
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
	

}
