package net.therap.service;

import net.therap.model.Booking;
import net.therap.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tanvirtareq
 * @since 7/13/23
 */
@Service
public class CustomerService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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

        Customer customer = this.findByEmail(email);

        if (customer == null) {
            return null;
        }

        if (!passwordEncoder.matches(password, customer.getPassword())) {
            return null;
        }

        return customer;
    }

    public List<Booking> findBookingList(Customer customer, LocalDate checkInDate, LocalDate checkOutDate,
                                         String hotelName, String roomType) {

        List<Booking> bookingList = customer.getBookings().stream()
                .filter(booking -> checkInDate == null || booking.getCheckInDate().equals(checkInDate))
                .filter(booking -> checkOutDate == null || booking.getCheckOutDate().equals(checkOutDate))
                .filter(booking -> hotelName == null || "".equals(hotelName)
                        || booking.getRoom().getHotel().getName().equals(hotelName))
                .filter(booking -> roomType == null || booking.getRoom().getType().equals(roomType))
                .collect(Collectors.toList());

        return bookingList;
    }

    @Transactional
    public void update(Long customerId, String name, String phoneNumber, LocalDate dateOfBirth) {

        Customer customer = entityManager.find(Customer.class, customerId);
        customer.setName(name);
        customer.setPhoneNumber(phoneNumber);
        customer.setDateOfBirth(dateOfBirth);

        entityManager.merge(customer);
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Transactional
    public void merge(Customer customer) {
        entityManager.merge(customer);
    }
}