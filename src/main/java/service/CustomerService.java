package service;

import model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();

    void save(Customer customer);

    Customer findById(int id);

    void update(Customer customer);

    void remove(Customer customer);

    List<Customer> findByName(String name);
}
