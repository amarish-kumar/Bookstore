package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bean.Book;

public class DAOapp {

	public static void main(String[] args) {
		BookstoreDAO books = new BookstoreDAOImp();
		List<Book> b =books.findAllBooks();
		Iterator<Book> it = b.iterator();
		while(it.hasNext()){
			System.out.println(it.next().toString());
		}

	}

}
