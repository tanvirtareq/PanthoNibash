package net.therap.service;

import net.therap.model.Customer;
import net.therap.model.Hotel;
import net.therap.model.Room;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author tanvirtareq
 * @since 7/16/23
 */
@Service
public class HotelService {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Hotel hotel) {
        entityManager.persist(hotel);
    }

    @Transactional
    public Hotel findById(Long id) {

        Hotel hotel = entityManager.find(Hotel.class, id);

        if (hotel != null) {
            Hibernate.initialize(hotel.getHotelImage());
        }

        return hotel;
    }

    @Transactional
    public Hotel findByEmail(String email) {

        String jpql = "SELECT h FROM Hotel h WHERE h.email=:email";

        List<Hotel> hotels = entityManager.createQuery(jpql, Hotel.class)
                .setParameter("email", email)
                .getResultList();

        if (hotels.size() == 0) {
            return null;
        }
        Hotel hotel = hotels.get(0);
        Hibernate.initialize(hotel.getHotelImage());

        return hotel;
    }

    @Transactional
    public Hotel findByEmailAndPassword(String email, String password) {
        String jpql = "SELECT h FROM Hotel h WHERE h.email=:email AND h.password=:password";
        List<Hotel> hotels = entityManager.createQuery(jpql, Hotel.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getResultList();

        if (hotels.size() == 0) {
            return null;
        }
        Hotel hotel = hotels.get(0);
        Hibernate.initialize(hotel.getHotelImage());

        return hotel;
    }

    @Transactional
    public void addRoom(Long hotelId, Room room) {
        Hotel hotel = entityManager.find(Hotel.class, hotelId);
        hotel.addRoom(room);
        entityManager.merge(hotel);
    }
}
