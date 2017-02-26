package model;

import java.io.Serializable;

public class AccountModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String login;
	private int address;
	private String fname;
	private String lname;
	private String pass;
	
	
	
	public int getAddress() {
		return address;
	}

	public void setAddress(int address) {
		this.address = address;
	}
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}


	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + address;
		result = prime * result + ((fname == null) ? 0 : fname.hashCode());
		result = prime * result + ((lname == null) ? 0 : lname.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountModel other = (AccountModel) obj;
		if (address != other.address)
			return false;
		if (fname == null) {
			if (other.fname != null)
				return false;
		} else if (!fname.equals(other.fname))
			return false;
		if (lname == null) {
			if (other.lname != null)
				return false;
		} else if (!lname.equals(other.lname))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (pass == null) {
			if (other.pass != null)
				return false;
		} else if (!pass.equals(other.pass))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AccountModel [login=" + login + ", address=" + address + ", fname=" + fname + ", lname=" + lname
				+ ", pass=" + pass + "]";
	}

	
	
	
	
}
