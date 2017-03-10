package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.Address;

public class AddressDAO {

	private DataSource ds;
	public AddressDAO() {
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/PROJECT");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	public void insertAddress(Address add) {
		
		try {
			String  updateText =
			             "INSERT INTO Address (street, province, country, zip, phone)"
			           + " VALUES (" 
			           + "\'" + add.getStreet() + "\',"
			           + "\'" + add.getProvince() + "\',"
			           + "\'" + add.getCountry() + "\',"
			           + "\'" + add.getZip() + "\',"
			           + "\'" + add.getPhone() + "\')";
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
	
	public Integer getAddressId(String street, String province, String country, String zip, String phone) {
		Integer id = null;
		try {
			String query =   "SELECT id"
			           + " FROM Address"
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
			Connection con = this.ds.getConnection();
			PreparedStatement p = con.prepareStatement(query);
			ResultSet answers = p.executeQuery();
			answers.next();
	        String res = answers.getString("id");
	        id = Integer.parseInt(res); 
			answers.close();
	    	p.close();
	    	con.close();

		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	public Address getAddress(int id) {
	   	Address address = new Address();
		try {
			String query =  "SELECT *"
			           + " FROM Address"
			           + " WHERE id="
			           + id;
			Connection con = this.ds.getConnection();
			PreparedStatement p = con.prepareStatement(query);
			ResultSet answers = p.executeQuery();
			
			answers.next();
	        int addid = Integer.parseInt(answers.getString("id"));
	        String street = answers.getString("street");
	        String province = answers.getString("province");
	        String country = answers.getString("country");
	        String zip = answers.getString("zip");
	        String phone = answers.getString("phone");

	        address.setId(addid);
	        address.setStreet(street);
	        address.setProvince(province);
	        address.setCountry(country);
	        address.setZip(zip);
	        address.setPhone(phone);
	    	 
	        answers.close();
	    	p.close();
			
			answers.close();
	    	p.close();
	    	con.close();

		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return address;
	}
}
