package model;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AdminDAO {


	private DataSource ds;
	public AdminDAO() {
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/PROJECT");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
