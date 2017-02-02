package model;

public class Book {
	
	private String bid;
	private String title;
	private int price;
	private String catergory;
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCatergory() {
		return catergory;
	}
	public void setCatergory(String catergory) {
		this.catergory = catergory;
	}
	
	@Override
	public String toString() {
		return "Book [bid=" + bid + ", title=" + title + ", price=" + price + ", catergory=" + catergory + "]";
	}
	
	
	
}
