package bean;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="purchaseOrderReport")
@XmlAccessorType(XmlAccessType.FIELD)
public class POReportWrapper implements  Serializable{
	

	private static final long serialVersionUID = 1L;
	@XmlElement
	private List<POWrapper> purchaseOrder;
	
	public POReportWrapper(){}
	public POReportWrapper(List<POWrapper> l ) {
		purchaseOrder = l;
	}

	
	

}
