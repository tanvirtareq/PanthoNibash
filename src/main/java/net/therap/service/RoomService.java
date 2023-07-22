package net.therap.service;

import net.therap.model.Booking;
import net.therap.model.Room;
import net.therap.util.Util;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author tanvirtareq
 * @since 7/19/23
 */
@Service
public class RoomService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Room> searchRooms(String parkingFacility, String wifiFacility, String fitnessCentre,
                                  String swimmingPool, String roomType, String hotelName, String location,
                                  Integer priceMin, Integer priceMax, Integer numberOfBed,
                                  LocalDate checkIn, LocalDate checkOut) {

        List<Room> rooms = getAll();

        List<Room> result = new ArrayList<>();

        for (Room room : rooms) {
            if (parkingFacility != null && !room.getHotel().getParkingFacility().equals(parkingFacility)) {
                continue;
            }

            if (wifiFacility != null && !room.getHotel().getWifiFacility().equals(wifiFacility)) {
                continue;
            }

            if (fitnessCentre != null && !room.getHotel().getFitnessCentre().equals(fitnessCentre)) {
                continue;
            }

            if (swimmingPool != null && !room.getHotel().getSwimmingPool().equals(swimmingPool)) {
                continue;
            }

            if (priceMin != null && room.getPrice() < priceMin) {
                continue;
            }

            if (priceMax != null && room.getPrice() > priceMax) {
                continue;
            }

            if (numberOfBed != null && !Objects.equals(room.getNumberOfBed(), numberOfBed)) {
                continue;
            }

            if (roomType != null && !room.getType().equals(roomType)) {
                continue;
            }

            if (hotelName != null && !Util.partialMatch(room.getHotel().getName(), hotelName)) {
                continue;
            }

            if (location != null && !Util.partialMatch(room.getHotel().getLocation(), location)) {
                continue;
            }

            if (!availableRoom(room, checkIn, checkOut)) {
                continue;
            }

            result.add(room);
        }

        return result;
    }

    public boolean availableRoom(Room room, LocalDate checkIn, LocalDate checkOut) {
        if (checkIn == null || checkOut == null) {
            return true;
        }
        for (String roomNumber : room.getRoomNumbers()) {
            List<Booking> bookings = getBookings(roomNumber, room);
            if (availableRoomNumber(bookings, checkIn, checkOut)) {
                return true;
            }
        }
        return false;
    }


    private boolean availableRoomNumber(List<Booking> bookings, LocalDate checkIn, LocalDate checkOut) {
        for (Booking booking : bookings) {
            if (isOverlap(checkIn, checkOut, booking.getCheckInDate()) || isOverlap(checkIn, checkOut, booking.getCheckOutDate())) {
                return false;
            }
        }
        return true;
    }

    private boolean isOverlap(LocalDate checkIn, LocalDate checkOut, LocalDate checkDate) {
        if (checkIn.equals(checkDate) || checkOut.equals(checkDate)) {
            return true;
        }

        if (checkIn.isBefore(checkDate) && checkOut.isAfter(checkDate)) {
            return true;
        }
        return false;
    }

    private List<Booking> getBookings(String roomNumber, Room room) {
        List<Booking> bookings = new ArrayList<>();
        for (Booking booking : room.getBookings()) {
            if (booking.getRoomNumber().equals(roomNumber)) {
                bookings.add(booking);
            }
        }
        return bookings;
    }

    private List<Room> getAll() {
        String jpql = "SELECT r FROM Room r";

        return entityManager.createQuery(jpql, Room.class).getResultList();
    }

    @Transactional
    public Room findById(Long id) {
        Room room = entityManager.find(Room.class, id);
        Hibernate.initialize(room.getRoomImage());
        Hibernate.initialize(room.getRoomNumbers());
        Hibernate.initialize(room.getBookings());

        return room;
    }

    public String getRoomNumber(Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        for (String roomNumber : room.getRoomNumbers()) {
            List<Booking> bookings = getBookings(roomNumber, room);
            if (availableRoomNumber(bookings, checkInDate, checkOutDate)) {
                return roomNumber;
            }
        }
        return "";
    }

//    @Transactional
//    public void addBooking(Room room, Booking booking) {
////        Room persistedRoom = entityManager.merge(room);
//        entityManager.persist(booking);
////        Booking persistedBooking = entityManager.merge(booking);
////        persistedRoom.addBooking(persistedBooking);
//    }
}
