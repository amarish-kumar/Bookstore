package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.POItem;

public class POItemDAO {

	private DataSource ds;
	public POItemDAO() {
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/PROJECT");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public void insertPOItem(POItem poi) {
		try {
			String  updateText =
					  "INSERT INTO POItem (id, bid, quan, price)"
					           + " VALUE (" 
					           + poi.getId() + ","
					    	   + "\'" + poi.getBid() + "\',"
					    	   + poi.getQuan() + ","
					    	   + poi.getPrice() + ")";  
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
