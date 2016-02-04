package main;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserDao {
    @PersistenceContext(unitName = "bookstore")
    private EntityManager em;
 
    public void save(User user){
        em.persist(user);
    }
 
    public List<User> findAll(){
        return em.createQuery("select u from User u")
                .getResultList();
    }
}