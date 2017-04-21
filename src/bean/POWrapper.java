package bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="purchaseOrder")
@XmlAccessorType(XmlAccessType.FIELD)
public class POWrapper implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@XmlAttribute
	private String orderDate;
	@XmlElement
	private String customerName;
	@XmlElement
	private Address shipTo;
	@XmlElement
	private List<Item> item;
	
	
	public POWrapper(){}
	public POWrapper(String name, String d, Address adr, List<Item> l) {
		customerName = name;
		orderDate = d;
		shipTo = adr;
		item = l;
	}

	
	

}
