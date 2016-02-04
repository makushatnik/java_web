package main;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class UserDao {
    @PersistenceContext(unitName = "bookstore")
    private EntityManager em;
 
    public List<User> getAll() {
        TypedQuery<User> namedQuery = em.createNamedQuery("User.getAll", User.class);
        return namedQuery.getResultList();
    }
    
    public User findById(Long id) {
		TypedQuery<User> query = em.createNamedQuery("User.findById", User.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}
 
    public User save(User user) {
    	if(user.getId() == null) {
			//LOG.info("Inserting new user");
			em.persist(user);
		} else {
			em.merge(user);
		}
    	return user;
    }
 
    public void delete(User user) {
    	User merged = em.merge(user);
		em.remove(merged);
    }
}