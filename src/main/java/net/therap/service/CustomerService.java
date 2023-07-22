package net.therap.service;

import net.therap.model.Customer;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author tanvirtareq
 * @since 7/13/23
 */
@Service
public class CustomerService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Customer customer) {
        entityManager.persist(customer);
    }

    @Transactional
    public Customer findById(Long id) {
        Customer customer = entityManager.find(Customer.class, id);

        return customer;
    }

    @Transactional
    public Customer findByEmail(String email) {

        String jpql = "SELECT c FROM Customer c WHERE c.email=:email";

        List<Customer> customers = entityManager.createQuery(jpql, Customer.class)
                .setParameter("email", email)
                .getResultList();

        if (customers.size() == 0) {
            return null;
        }
        Customer customer = customers.get(0);

        return customer;
    }

    @Transactional
    public Customer findByEmailAndPassword(String email, String password) {
        String jpql = "SELECT c FROM Customer c WHERE c.email=:email AND c.password=:password";
        List<Customer> customers = entityManager.createQuery(jpql, Customer.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getResultList();

        if (customers.size() == 0) {
            return null;
        }
        Customer customer = customers.get(0);

        return customer;
    }
}