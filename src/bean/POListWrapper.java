package bean;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="purchaseOrderReport")
@XmlAccessorType(XmlAccessType.FIELD)
public class POListWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement
	private List<POWrapper> purchaseOrderList;

	public POListWrapper(List<POWrapper> l) {
		purchaseOrderList = l;
	}

	/**
	 * @return the purchaseOrderList
	 */
	public List<POWrapper> getPurchaseOrderList() {
		return purchaseOrderList;
	}

	/**
	 * @param purchaseOrderList the purchaseOrderList to set
	 */
	public void setPurchaseOrderList(List<POWrapper> purchaseOrderList) {
		this.purchaseOrderList = purchaseOrderList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((purchaseOrderList == null) ? 0 : purchaseOrderList.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		POListWrapper other = (POListWrapper) obj;
		if (purchaseOrderList == null) {
			if (other.purchaseOrderList != null)
				return false;
		} else if (!purchaseOrderList.equals(other.purchaseOrderList))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "POListWrapper [purchaseOrderList=" + purchaseOrderList + "]";
	}
	
	

}
