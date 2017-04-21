package service;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import DAO.BookDAO;
import bean.Book;

@Path("/CIService")
public class CatalogInfoRest {

private final static BookDAO  BDAO = new BookDAO();
	
	@GET
	@Path("/Catalog/{pid}")
	@Produces("text/html")
	public String getProductInfo(@PathParam("pid") String pid) throws SQLException{
		Book b = BDAO.getBookById(pid);
		return "<html lang=\"en\"><body><h1>"+b.toString()+ "</h1></body></html>";
	}


}
