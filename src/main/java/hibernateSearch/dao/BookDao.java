package hibernateSearch.dao;

import java.util.List;

import hibernateSearch.model.Book;

public interface BookDao {
	public void addBook(Book book);

	public Book getBook(String title);

	public void deleteBook(Book book);

	public List<Book> listAllBook();

	public List<Book> searchBook(String query);
}
