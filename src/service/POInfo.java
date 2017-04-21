package service;

import java.util.List;
import java.util.Map;

import DAO.PODAO;
import DAO.POItemDAO;
import bean.Address;
import bean.Item;
import bean.PO;
import bean.POItem;
import bean.POWrapper;

public class POInfo {

	private final static PODAO  PDAO = new PODAO();
	private final static POItemDAO  POIDAO = new POItemDAO();

	public Integer getPOId(int month, String lname, String fname, String status, int address) {
		return PDAO.getPOId(month, lname, fname, status, address);
	}
	
	public Integer getPOId2(int month, String lname, String fname, String status, int address) {
		return PDAO.getPOId2(month, lname, fname, status, address);
	}
	
	public PO sanitizePO(PO po) throws Exception{
		if(po == null){
			throw new Exception("process order is null");
		}
		return po;
	}
	
	public void insertPO(PO po) throws Exception {
		PO sanitizedPO = sanitizePO(po);
		PDAO.insertPO(sanitizedPO);
	}
	
	public void updatePO(PO po) throws Exception {
		PO sanitizedPO = sanitizePO(po);
		PDAO.updatePO(sanitizedPO);
	}
	
	public Integer sanitizeMonth(int m) throws Exception{
		
		if(m < 0 || m > 12){
			throw new Exception("CREDIT_TAKEN must be between 0-12");
		}
		return m;
		
	}
	
	public List<PO> getPOByMonth(int m) throws Exception{
		int sanitizedMonth = sanitizeMonth(m);
		return PDAO.getPOByMonth(sanitizedMonth);
	}
	
	public POItem sanitizePOItem(POItem poi) throws Exception{
		if(poi == null){
			throw new Exception("process order item is null");
		}
		return poi;
	}
	public void insertPOItem(POItem poi) throws Exception {
		POItem sanitizedPOItem = sanitizePOItem(poi);
		POIDAO.insertPOItem(sanitizedPOItem);
	}
	
	public String sanitizeBid(String bid){
		if (bid == null)
			return "";
		return bid;
	}
	public List<POWrapper> getOrdersByBid(String bid){
		String sanitizedBid = sanitizeBid(bid);
		return PDAO.getOrdersByBid(sanitizedBid);
		
	}
	
	public int sanitizePOid(int poid) throws Exception{
		if(poid < 0){
			throw new Exception("process order id is negative");
		}
		return poid;
	}
	
	public Address getAddressByPOid(Integer poid) throws Exception{
		int sanitizedpoid = sanitizePOid(poid);
		return PDAO.getAddressByPOid(poid);
	}



	


}
