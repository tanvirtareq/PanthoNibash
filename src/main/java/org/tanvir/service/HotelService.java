package org.tanvir.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.tanvir.model.*;

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

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Hotel hotel) {
        entityManager.persist(hotel);
    }

    public Hotel findById(Long id) {

        return entityManager.find(Hotel.class, id);
    }

    public Hotel findByEmail(String email) {

        String jpql = "SELECT h FROM Hotel h WHERE h.email=:email";

        return entityManager.createQuery(jpql, Hotel.class)
                .setParameter("email", email)
                .getResultStream().findFirst().orElse(null);
    }

    @Transactional
    public Hotel findByEmailAndPassword(String email, String password) {

        Hotel hotel = this.findByEmail(email);

        if (hotel == null) {
            return null;
        }

        if (!passwordEncoder.matches(password, hotel.getPassword())) {
            return null;
        }

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

        hotel.getRooms().forEach(room -> room.getBookings().stream()
                .filter(booking -> checkInDate == null
                        || booking.getCheckInDate().equals(checkInDate))
                .filter(booking -> checkOutDate == null
                        || booking.getCheckOutDate().equals(checkOutDate))
                .filter(booking -> roomNumber == null
                        || roomNumber.isEmpty()
                        || booking.getRoomNumber().equals(roomNumber))
                .filter(booking -> customerName == null
                        || customerName.isEmpty()
                        || booking.getCustomer().getName().equals(customerName))
                .filter(booking -> customerEmail == null
                        || customerEmail.isEmpty()
                        || booking.getCustomer().getEmail().equals(customerEmail))
                .filter(booking -> roomType == null
                        || booking.getRoom().getType().equals(roomType))
                .forEach(bookingList::add));

        return bookingList;
    }

    @Transactional
    public void update(Long hotelId, String name, String phoneNumber, String location,
                       String parkingFacility, String swimmingPool, String wifiFacility) {

        Hotel hotel = entityManager.find(Hotel.class, hotelId);
        hotel.setName(name);
        hotel.setPhoneNumber(phoneNumber);
        hotel.setLocation(location);
        hotel.setParkingFacility(parkingFacility);
        hotel.setSwimmingPool(swimmingPool);
        hotel.setWifiFacility(wifiFacility);

        entityManager.merge(hotel);
    }

    public List<Hotel> getAll() {
        return entityManager.createQuery("SELECT h FROM Hotel h", Hotel.class).getResultList();
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public List<String> getHotelsNameByPartialName(String hotelName) {

        List<String> hotelList = entityManager
                .createQuery("SELECT h.name FROM Hotel h WHERE LOWER(h.name) LIKE LOWER(:hotelName)", String.class)
                .setParameter("hotelName", "%" + hotelName + "%").getResultList();

        if (hotelList.isEmpty()) {
            hotelList = getAllHotelName();
        }

        return hotelList;
    }

    private List<String> getAllHotelName() {
        return entityManager.createQuery("SELECT h.name FROM Hotel h", String.class)
                .getResultList();
    }

    @Transactional
    public void merge(Hotel hotel) {
        entityManager.merge(hotel);
    }

    public boolean hasBookingManipulationAuthorization(Long hotelId, Long bookingId, SessionContext sessionContext) {

        Hotel hotel = entityManager.find(Hotel.class, hotelId);

        return hotelLoggedIn(sessionContext, hotelId) && hotel.getRooms()
                .stream()
                .anyMatch(room -> hasBookingId(room, bookingId));
    }

    private boolean hotelLoggedIn(SessionContext sessionContext, Long hotelId) {
        return sessionContext != null && Role.HOTEL.equals(sessionContext.getRole()) && hotelId.equals(sessionContext.getId());
    }

    private boolean hasBookingId(Room room, Long bookingId) {
        return room.getBookings().stream().anyMatch(booking -> booking.getId().equals(bookingId));
    }

    public boolean emailExists(String email) {
        return entityManager.createQuery("SELECT COUNT (email) FROM Hotel h where email = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult() > 0;
    }
}