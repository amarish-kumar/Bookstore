package model;

import java.util.List;

public interface BookstoreDAO {
	
	public List<Book> findAllBooks();
	public List<Book> findBookByCatergory(String c);
	public void insert(Book book);
	public void insertAddress(Address add);
	public void insertAccount(AccountModel acc);
	public int getAddressId(String street, String province, String country, String zip, String phone);
	public void update(Book book);
	public void delete(String bid);
	public Book findBookById(String bid);
}
