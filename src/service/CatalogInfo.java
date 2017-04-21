package service;

import java.sql.SQLException;
import java.util.List;

import DAO.BookDAO;
import DAO.ReviewDAO;
import bean.Book;
import bean.ReviewBean;

public class CatalogInfo {
	
	
	private final static BookDAO  BDAO = new BookDAO();
	private final static ReviewDAO  RDAO = new ReviewDAO();

	
	public String getProductInfo(String pid) throws SQLException{
		Book b = BDAO.getBookById(pid);
		return b.toString();
	}
	
	public List<Book> getAllBooks(){
		return BDAO.getAllBooks();
	}
	
	public Book getMostPopularBook(){
		return BDAO.getMostPopularBook();
	}
	
	public Integer sanitizeMonth(int m) throws Exception{
		
		if(m < 0 || m > 12){
			throw new Exception("month must be between 0-12");
		}
		return m;
		
	}
	
	public List<Book> getBookSoldByMonth(int m) throws Exception{
		int month = sanitizeMonth(m);
		return BDAO.getBookSoldByMonth(month);
	
	}
	
	public String sanitizeCatergory(String c){
		if (c == null)
			return "";
		return c;
		
	}
	
	public List<Book> getBookByCatergory(String c) {
		String cat = sanitizeCatergory(c);
		return BDAO.getBookByCatergory(cat);	
	}

	public Integer sanitizeQuantity(Integer quan) throws Exception{
		if(quan < 0){
			throw new Exception("quantity must be non negative");
		}
		return quan;
	}
	
	public Book sanitizeBook(Book b) throws Exception{
		if(b == null){
			throw new Exception("book is null");
		}
		return b;
	}
	
	public void updateBook(Book b, Integer quan) throws Exception {
		Book sanitizedBook = sanitizeBook(b);
		Integer sanitizedQuan =  sanitizeQuantity(quan);
		BDAO.updateBook(sanitizedBook, sanitizedQuan);
	}
	
	public String sanitizeBid(String bid){
		if (bid == null)
			return "";
		return bid;
	}
	public Book getBookById(String bid) throws SQLException {
		String sanitizedBid = sanitizeBid(bid);
		return BDAO.getBookById(sanitizedBid);
	}
	
	public List<ReviewBean> getReviewsByBid(String bid){
		String sanitizedBid = sanitizeBid(bid);
		return RDAO.getReviewsByBid(sanitizedBid);

	}
	
	public void insertReview( String bid, String login, String rev, String rating) {
		RDAO.insertReview(bid, login, rev, rating);
	}
	
	public Double getAverageRatingByBid(String bid){
		String sanitizedBid = sanitizeBid(bid);
		return RDAO.getAverageRatingByBid(sanitizedBid);

	}
	
	public String sanitizeTitle(String t){
		if (t == null)
			return "";
		return t;
	}
	
	public  List<Book>  getBookByTitle(String t){
		String sanitizedTitle = sanitizeTitle(t);
		return BDAO.getBookByTitle(sanitizedTitle);
	}
	
	public String sanitizeSearchText(String t){
		if (t == null)
			return "";
		return t;
	}
	
	public  List<Book>  searchBooks(String t){
		String sanitizedSearchText =  sanitizeSearchText(t);
		return BDAO.searchBooks(sanitizedSearchText);
	}
	
	



}
