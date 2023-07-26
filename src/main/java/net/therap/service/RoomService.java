package net.therap.service;

import net.therap.model.Booking;
import net.therap.model.Room;
import net.therap.util.Util;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

        List<Room> result = rooms.stream()
                .filter(room -> parkingFacility == null || room.getHotel().getParkingFacility().equals(parkingFacility))
                .filter(room -> wifiFacility == null || room.getHotel().getWifiFacility().equals(wifiFacility))
                .filter(room -> fitnessCentre == null || room.getHotel().getFitnessCentre().equals(fitnessCentre))
                .filter(room -> swimmingPool == null || room.getHotel().getSwimmingPool().equals(swimmingPool))
                .filter(room -> priceMin == null || room.getPrice() >= priceMin)
                .filter(room -> priceMax == null || room.getPrice() <= priceMax)
                .filter(room -> numberOfBed == null || Objects.equals(room.getNumberOfBed(), numberOfBed))
                .filter(room -> roomType == null || room.getType().equals(roomType))
                .filter(room -> hotelName == null || Util.partialMatch(room.getHotel().getName(), hotelName))
                .filter(room -> location == null || Util.partialMatch(room.getHotel().getLocation(), location))
                .filter(room -> availableRoom(room, checkIn, checkOut)).collect(Collectors.toList());

        return result;
    }

    public boolean availableRoom(Room room, LocalDate checkIn, LocalDate checkOut) {

        if (checkIn == null || checkOut == null) {
            return true;
        }

        return room.getRoomNumbers().stream().map(roomNumber -> getBookings(roomNumber, room))
                .anyMatch(bookings -> availableRoomNumber(bookings, checkIn, checkOut));
    }

    private boolean availableRoomNumber(List<Booking> bookings, LocalDate checkIn, LocalDate checkOut) {

        return bookings.stream().noneMatch(booking -> isOverlap(checkIn, checkOut, booking.getCheckInDate()) ||
                isOverlap(checkIn, checkOut, booking.getCheckOutDate()));
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

        List<Booking> bookings = room.getBookings().stream()
                .filter(booking -> booking.getRoomNumber().equals(roomNumber))
                .collect(Collectors.toList());

        return bookings;
    }

    public List<Room> getAll(Long curPage) {
        int roomPerPage = 5;
        int startIdx = (int) ((curPage - 1) * roomPerPage);

        String jpql = "SELECT r FROM Room r ORDER BY r.id";

        return entityManager.createQuery(jpql, Room.class).setFirstResult(startIdx)
                .setMaxResults(roomPerPage).getResultList();

    }

    public List<Room> getAll() {
        String jpql = "SELECT r FROM Room r";

        return entityManager.createQuery(jpql, Room.class).getResultList();
    }

    @Transactional
    public Room findById(Long id) {
        Room room = entityManager.find(Room.class, id);

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

    @Transactional
    public void update(Long roomId, String type, Long price, Integer numberOfBed) {

        Room room = entityManager.find(Room.class, roomId);
        room.setType(type);
        room.setPrice(price);
        room.setNumberOfBed(numberOfBed);

        entityManager.merge(room);
    }
}