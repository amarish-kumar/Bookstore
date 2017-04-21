package service;

import java.util.List;
import java.util.Map;

import DAO.AdminDAO;
import bean.Book;

public class AdminService {

	private final static AdminDAO  ADAO = new AdminDAO();

	public Book getMostPopularBook(){
		return ADAO.getMostPopularBook();
	}
	
	public Map<String,Integer> getUserSpents(){
		return ADAO.getUserSpents();
	}
	
	public Integer sanitizeMonth(int m) throws Exception{
			
		if(m < 0 || m > 12){
			throw new Exception("month must be between 0-12");
		}
		return m;
			
	}
	
	public List<Book> getBookSoldByMonth(int m) throws Exception{
		int month = sanitizeMonth(m);
		return ADAO.getBookSoldByMonth(month);
	
	}
}
