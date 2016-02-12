package org.jboss.bookstore.database;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.jboss.bookstore.ejb.BookEJB;
import org.jboss.bookstore.model.Book;

@Singleton
@Startup
@DataSourceDefinition(
  className = "com.mysql.jdbc.Driver",
  name = "java:global/jdbc/bookstoreDS",
  user = "dbuser",
  password = "s3cret",
  databaseName = "bookstore",
  properties = {"connectionAtributes=:create=true"}
)
public class DatabasePopulator {
	@Inject
	private BookEJB bookEJB;
	private Book h2g2;
	private Book lord;
	@PostConstruct
	private void populateDB() {
		h2g2 = new Book("Изучаем Java EE", 35F, "Великая книга", "1-8763-9125-7", 605, true);
		lord = new Book("Властелин колец", 50.4F, "Фэнтези", "1-84023-742-2", 1214, true);
		bookEJB.createBook(h2g2);
		bookEJB.createBook(lord);
	}
	@PreDestroy
	private void clearDB() {
		bookEJB.deleteBook(h2g2);
		bookEJB.deleteBook(lord);
	}
}
