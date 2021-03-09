package service;

import model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.*;

@Transactional
public class CustomerServiceImpl implements CustomerService {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Customer> findAll() {
        String query = "SELECT c FROM Customer AS c";
        TypedQuery<Customer> query1 = entityManager.createQuery(query, Customer.class);
        return query1.getResultList();
    }

    @Override
    public void save(Customer customer) {
        entityManager.persist(customer);
    }

    @Override
    public Customer findById(int id) {
     return entityManager.find(Customer.class, id);
    }

    @Override
    public void update(Customer customer) {
        entityManager.merge(customer);
    }

    @Override
    public void remove(Customer customer) {
      //  entityManager.remove(entityManager.contains(customer) ? customer : entityManager.merge(customer));
       if (customer != null){
           entityManager.remove(findById(customer.getId()));
       }
    }

    @Override
    public List<Customer> findByName(String name) {
        String query = "SELECT c FROM Customer AS c WHERE c.name = :name";
        TypedQuery<Customer> query1 = entityManager.createQuery(query, Customer.class);
        query1.setParameter("name", name);
        return query1.getResultList();
    }
}
