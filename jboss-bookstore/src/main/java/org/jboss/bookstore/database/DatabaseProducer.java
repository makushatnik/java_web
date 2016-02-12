package org.jboss.bookstore.database;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DatabaseProducer {
	@Produces
	@PersistenceContext(unitName = "bookstore")
	private EntityManager em;
}
