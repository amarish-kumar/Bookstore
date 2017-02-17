package model;

import java.util.List;

public interface BookstoreDAO {
	
	public List<Book> findAllBooks();
	public List<Book> findBookByCatergory(String c);
	public void insert(Book book);
	public void update(Book book);
	public void delete(String bid);
	public Book findBookById(String bid);
}
