package net.therap.service;

import net.therap.model.Booking;
import net.therap.model.Hotel;
import net.therap.model.Room;
import net.therap.model.SessionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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

        if (hotels.isEmpty()) {
            return null;
        }

        Hotel hotel = hotels.get(0);

        return hotel;
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

        List<Hotel> hotels = entityManager.createQuery("SELECT h FROM Hotel h", Hotel.class).getResultList();

        return hotels;
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public List<String> getHotelsNameByPartialName(String hotelName) {

        hotelName = hotelName;

        List<String> hotelList = entityManager
                .createQuery("SELECT h.name FROM Hotel h WHERE LOWER(h.name) LIKE LOWER(:hotelName)", String.class)
                .setParameter("hotelName", "%" + hotelName + "%").getResultList();

        if (hotelList.size() == 0) {
            hotelList = getAllHotelName();
        }

        return hotelList;
    }

    private List<String> getAllHotelName() {

        List<String> hotels = entityManager.createQuery("SELECT h.name FROM Hotel h", String.class)
                .getResultList();

        return hotels;
    }

    @Transactional
    public void merge(Hotel hotel) {
        entityManager.merge(hotel);
    }

    public boolean hasBookingManipulationAuthorization(Long hotelId, Long bookingId, SessionContext sessionContext) {

        Hotel hotel = entityManager.find(Hotel.class, hotelId);

        return hotelLoggedIn(sessionContext, hotelId )
                && hotel.getRooms().stream().anyMatch(room -> hasBookingId(room, bookingId));
    }

    private boolean hotelLoggedIn(SessionContext sessionContext, Long hotelId) {
        return sessionContext!=null && "HOTEL".equals(sessionContext.getRole())
                && hotelId.equals(sessionContext.getId());
    }

    private boolean hasBookingId(Room room, Long bookingId) {
        return room.getBookings().stream().anyMatch(booking -> booking.getId().equals(bookingId));
    }
}