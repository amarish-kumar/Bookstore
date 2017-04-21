package bean;

import java.io.Serializable;

public class ReviewBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private int rid;
	private String bid;
	private String login;
	private String rev;
	private int rating;
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getRev() {
		return rev;
	}
	public void setRev(String rev) {
		this.rev = rev;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bid == null) ? 0 : bid.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + rating;
		result = prime * result + ((rev == null) ? 0 : rev.hashCode());
		result = prime * result + rid;
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
		ReviewBean other = (ReviewBean) obj;
		if (bid == null) {
			if (other.bid != null)
				return false;
		} else if (!bid.equals(other.bid))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (rating != other.rating)
			return false;
		if (rev == null) {
			if (other.rev != null)
				return false;
		} else if (!rev.equals(other.rev))
			return false;
		if (rid != other.rid)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ReviewBean [rid=" + rid + ", bid=" + bid + ", login=" + login + ", rev=" + rev + ", rating=" + rating
				+ "]";
	}
	
	
	
	

}
