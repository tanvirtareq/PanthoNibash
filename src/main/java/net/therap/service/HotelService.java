package net.therap.service;

import net.therap.model.Booking;
import net.therap.model.Hotel;
import net.therap.model.Room;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
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

        return hotel;
    }

    @Transactional
    public void addRoom(Long hotelId, Room room) {
        Hotel hotel = entityManager.find(Hotel.class, hotelId);
        hotel.addRoom(room);
        entityManager.merge(hotel);
    }

    public List<Booking> findBookingList(Hotel hotel, LocalDate checkInDate, LocalDate checkOutDate, String roomNumber,
                                         String customerName, String customerEmail, String roomType) {
        List<Booking> bookingList = new ArrayList<>();

        for (Room room : hotel.getRooms()) {
            for (Booking booking : room.getBookings()) {
                if (checkInDate != null && !booking.getCheckInDate().equals(checkInDate)) {
                    continue;
                }

                if (checkOutDate != null && !booking.getCheckOutDate().equals(checkOutDate)) {
                    continue;
                }

                if (roomNumber != null && !"".equals(roomNumber) && !booking.getRoomNumber().equals(roomNumber)) {
                    continue;
                }

                if (customerName != null && !"".equals(customerName) && !booking.getCustomer().getName().equals(customerName)) {
                    continue;
                }

                if (customerEmail != null && !"".equals(customerEmail) && !booking.getCustomer().getEmail().equals(customerEmail)) {
                    continue;
                }
                if (roomType != null && !booking.getRoom().getType().equals(roomType)) {
                    continue;
                }

                bookingList.add(booking);
            }
        }

        return bookingList;
    }
}
