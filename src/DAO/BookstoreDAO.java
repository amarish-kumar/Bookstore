package DAO;

import java.util.List;

import bean.AccountModel;
import bean.Address;
import bean.Book;
import bean.CreditCard;
import bean.PO;
import bean.POItem;

public interface BookstoreDAO {
	
	public List<Book> findAllBooks();
	public List<Book> findBookByCatergory(String c);
	public void insert(Book book);
	public void insertAddress(Address add);
	public void insertAccount(AccountModel acc);
	public void insertPO(PO po);
	public void insertPOItem(POItem poi);
	public int getAddressId(String street, String province, String country, String zip, String phone);
	public void update(Book book);
	public void delete(String bid);
	public Book findBookById(String bid);
	public String getPassword(String login);
	public int getAccountAddress(String login);
	public AccountModel getAccount(String login);
	public String getLname(String login);
	public String getFname(String login);
	public int getPOId(String lname, String fname, String status, int address);
	public Address getAddress(int id);
	public CreditCard getCreditCard(int num);
}
