package net.therap.service;

import net.therap.model.Booking;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * @author tanvirtareq
 * @since 7/20/23
 */
@Service
public class BookingService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Booking booking) {
        entityManager.persist(booking);
    }

    public Booking findById(Long bookingId) {
        return entityManager.find(Booking.class, bookingId);
    }

    @Transactional
    public void merge(Booking booking) {
        entityManager.merge(booking);
    }

    @Transactional
    public void delete(Booking booking) {
        entityManager.remove(booking);
    }

    @Transactional
    public void update(Booking booking) {
        entityManager.merge(booking);
    }
}