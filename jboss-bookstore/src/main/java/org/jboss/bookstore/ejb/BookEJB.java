package org.jboss.bookstore.ejb;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import org.jboss.bookstore.model.Book;

@Named//для JCF
@Stateless
@DataSourceDefinition(name="java:global/jdbc/bookstoreDS",
		className="com.mysql.jdbc.Driver",
		url="jdbc:mysql://localhost:3306/testdb")
public class BookEJB {
	//@PersistenceContext(unitName = "bookstore")
	@Inject
	private EntityManager em;
	
	public BookEJB() {}
	
	public Book findBookById(Long id) {
		return em.find(Book.class, id);
	}
	
	public @NotNull Book createBook(@NotNull Book book) {
		em.persist(book);
		return book;
	}
	
	public List<Book> findBooks() {
		TypedQuery<Book> query = em.createNamedQuery("findAllBooks", Book.class);
		return query.getResultList();
	}
	
	@RolesAllowed("admin")
	public void deleteBook(@NotNull Book book) {
		em.remove(em.merge(book));
	}
	
	public @NotNull Book updateBook(@NotNull Book book) {
		return em.merge(book);
	}	
}
