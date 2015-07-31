package hibernateSearch.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hibernateSearch.dao.BookDao;
import hibernateSearch.model.Book;

@Repository
@Transactional
public class BookDaoImpl implements BookDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addBook(Book book) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(book);

	}

	@Override
	public Book getBook(String title) {
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Book.class);
		cr.add(Restrictions.like("title", title));
		return (Book) cr.uniqueResult();
	}

	@Override
	public void deleteBook(Book book) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(book);

	}

	@Override
	public List<Book> listAllBook() {
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Book.class);
		return cr.list();
	}

	@Override
	public List<Book> searchBook(String key) {
		FullTextSession fullTextSession = Search
				.getFullTextSession(sessionFactory.getCurrentSession());
		QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder()
				.forEntity(Book.class).get();
		org.apache.lucene.search.Query query = qb.keyword()
				.onFields("title", "author.name", "author.name", "language")
				.matching(key).createQuery();
		org.hibernate.Query hibQuery = fullTextSession
				.createFullTextQuery(query, Book.class);
		List result = hibQuery.list();
		return result;
	}

}
