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

import bean.Address;
import bean.Book;
import bean.Item;
import bean.PO;
import bean.POWrapper;

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
	
	public List<POWrapper> getOrdersByBid(String bid){
		 //Map<Integer,List<Item>> map = new HashMap<Integer,List<Item>>();
		List<POWrapper> list = new ArrayList<POWrapper>();
		try {
			String query =    "SELECT poi.id, p.fname, p.lname, p.day"
			           + " FROM POItem poi, PO p"
			           + " WHERE poi.bid ="
			           + " '" + bid + "' and"
			           + " p.id = poi.id";
			         				
			Connection con = this.ds.getConnection();
			PreparedStatement p = con.prepareStatement(query);
			ResultSet answers = p.executeQuery();
			while (answers.next()) {
				
				Integer poid = Integer.parseInt(answers.getString("id"));
				String name =  answers.getString("lname") + "," + answers.getString("fname");
				//Integer month = Integer.parseInt(answers.getString("month"));
				List<Item> l = getItemsByPOid(poid);
				Address adr = getAddressByPOid(poid);
				String day = answers.getString("day");
			    POWrapper pow = new POWrapper(name,day,adr,l);
			    list.add(pow);
			     
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
	
	public List<Item> getItemsByPOid(Integer poid){
		List<Item> list = new ArrayList<Item>();
		try {
			String query =    "SELECT b.title, poi.bid, poi.quan, poi.price"
			           + " FROM PO p, POItem poi, Book b"
			           + " WHERE poi.id="
			           + poid + " and"
			           + " p.id = poi.id and"
			           + " poi.bid = b.bid";
			           
			         
			           
						
			Connection con = this.ds.getConnection();
			PreparedStatement p = con.prepareStatement(query);
			ResultSet answers = p.executeQuery();
			while (answers.next()) {
				
			     
			     String prodName = answers.getString("title");
			     Integer quan = Integer.parseInt(answers.getString("quan"));
			     Integer price = Integer.parseInt(answers.getString("price"));
			     String bid1 = answers.getString("bid");
			     Item it = new Item(prodName,quan,price,bid1);
		
			  
			     list.add(it);
			 	
			     
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
	

	public Address getAddressByPOid(Integer poid){
		Address adr = new Address();
		try {
			String query =    "SELECT *"
			           + " FROM (select p.address"
			           + " 			from po p"
			           + " 			where p.id =" + poid +") as t, address a"
			           + " WHERE t.address = a.id";
			          			
			Connection con = this.ds.getConnection();
			PreparedStatement p = con.prepareStatement(query);
			ResultSet answers = p.executeQuery();
			if(answers.next()){
				String country = answers.getString("country");
				String street = answers.getString("street");
				String province = answers.getString("province");
				String zip = answers.getString("zip");
				String phone = answers.getString("phone");
				adr.setId(Integer.parseInt(answers.getString("id")));
				adr.setCountry(country);
				adr.setStreet(street);
				adr.setProvince(province);
				adr.setZip(zip);
				adr.setPhone(phone);
			}
			answers.close();
	    	p.close();
	    	con.close();

		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return adr;
		
	}


}
