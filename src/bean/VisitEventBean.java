package bean;

public class VisitEventBean {

	private String day;
	private String bid;
	private String eventtype;
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getEventtype() {
		return eventtype;
	}
	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}
	@Override
	public String toString() {
		return "VisitEvent [day=" + day + ", bid=" + bid + ", eventtype=" + eventtype + "]";
	}
	
	
}
