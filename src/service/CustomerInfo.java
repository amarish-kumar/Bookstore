package service;

import DAO.AccountDAO;
import DAO.AddressDAO;
import DAO.BookDAO;
import DAO.CreditCardDAO;
import bean.AccountModel;
import bean.Address;
import bean.Book;
import bean.CreditCard;

public class CustomerInfo {
	
	private final static AccountDAO  ACCDAO = new AccountDAO();
	private final static AddressDAO  ADDDAO = new AddressDAO();
	private final static CreditCardDAO  CCDAO = new CreditCardDAO();

	
	public String sanitizeLogin(String login){
		if (login == null)
			return "";
		return login;
		
	}
	public AccountModel getAccount(String login) {
		String sanitizedLogin = sanitizeLogin(login);
		return ACCDAO.getAccount(sanitizedLogin);
	}
	
	public String getPassword(String login) { 
		String sanitizedLogin = sanitizeLogin(login);
		return ACCDAO.getPassword(sanitizedLogin);
	}
	
	public Integer getAccountAddress(String login) {
		String sanitizedLogin = sanitizeLogin(login);
		return ACCDAO.getAccountAddress(sanitizedLogin);
	}
	
	public AccountModel sanitizeAccount(AccountModel acc) throws Exception{
		if(acc == null){
			throw new Exception("account is null");
		}
		return acc;
	}
	public void insertAccount(AccountModel acc) throws Exception {
		AccountModel sanitizedAccount = sanitizeAccount(acc);
		ACCDAO.insertAccount(sanitizedAccount);
	}
	
	public Address sanitizeAddress(Address add) throws Exception{
		if(add == null){
			throw new Exception("account is null");
		}
		return add;
	}
	public void insertAddress(Address add) throws Exception {
		Address sanitizedAddress = sanitizeAddress(add);
		ADDDAO.insertAddress(sanitizedAddress);

	}
	
	public Integer getAddressId(String street, String province, String country, String zip, String phone) {
		return ADDDAO.getAddressId(street,province,country,zip,phone);
	}
	
	public Integer sanitizeId(int id) throws Exception{
		
		if(id < 0){
			throw new Exception("Id is negative");
		}
		return id;
		
	}
	public Address getAddress(int id) throws Exception {
		int sanitizedId = sanitizeId(id);
		return ADDDAO.getAddress(sanitizedId);
	}
	
	public Integer sanitizeCC(int num) throws Exception{
		
		if(num < 0){
			throw new Exception("credit card number is negative");
		}
		return num;
		
	}
	public CreditCard getCreditCard(int num) throws Exception {
		int sanitizedCreditCardNumber = sanitizeCC(num);
		return CCDAO.getCreditCard(sanitizedCreditCardNumber);
	}



		

	

}
