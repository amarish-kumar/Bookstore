package bean;

import java.io.Serializable;

public class POItem implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String bid;
	private int quan;
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
	
	public int getQuan() {
		return quan;
	}
	public void setQuan(int quan) {
		this.quan = quan;
	}
	@Override
	public String toString() {
		return "POItem [id=" + id + ", bid=" + bid + ", price=" + price + "]";
	}
	
	
}
