package model;

public class PO {
	private int id;
	private String lname;
	private String fname;
	private String status;
	private int address;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getAddress() {
		return address;
	}
	public void setAddress(int address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "PO [id=" + id + ", lname=" + lname + ", fname=" + fname + ", status=" + status + ", address=" + address
				+ "]";
	}
	
	
}
