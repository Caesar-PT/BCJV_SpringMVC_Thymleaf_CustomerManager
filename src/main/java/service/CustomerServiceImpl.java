package service;

import model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.*;

public class CustomerServiceImpl implements CustomerService {
    private static Map<Integer, Customer> customers;

    static {
        customers = new HashMap<>();
        customers.put(1, new Customer(1, "John", "john@codegym.vn", "Hanoi"));
        customers.put(2, new Customer(2, "Bill", "bill@codegym.vn", "Danang"));
        customers.put(3, new Customer(3, "Alex", "alex@codegym.vn", "Saigon"));
        customers.put(4, new Customer(4, "Adam", "adam@codegym.vn", "Beijin"));
        customers.put(5, new Customer(5, "Sophia", "sophia@codegym.vn", "Miami"));
        customers.put(6, new Customer(6, "Rose", "rose@codegym.vn", "Newyork"));
    }

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> findAll() {
        String query = "SELECT c FROM Customer AS c";
        TypedQuery<Customer> query1 = sessionFactory.openSession().createQuery(query, Customer.class);
        return query1.getResultList();
    }

    @Override
    public void save(Customer customer) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
            return;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return;
    }

    @Override
    public Customer findById(int id) {
        String query = "SELECT c FROM Customer AS c WHERE c.id = :id";
        TypedQuery<Customer> query1 = entityManager.createQuery(query, Customer.class);
        query1.setParameter("id", id);
        return query1.getSingleResult();
    }

    @Override
    public void update(Customer customer) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(customer);
            transaction.commit();
            return;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return;
    }

    @Override
    public void remove(Customer customer) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(customer);
            transaction.commit();
            return;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return;
    }

    @Override
    public List<Customer> findByName(String name) {
        String query = "SELECT c FROM Customer AS c WHERE c.name = :name";
        TypedQuery<Customer> query1 = entityManager.createQuery(query, Customer.class);
        query1.setParameter("name", name);
        return  query1.getResultList();
    }
}
