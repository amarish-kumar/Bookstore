package model;

public class POItem {
	
	private int id;
	private String bid;
	private int price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "POItem [id=" + id + ", bid=" + bid + ", price=" + price + "]";
	}
	
	
}
