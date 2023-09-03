package org.tanvir.service;

import org.tanvir.dto.SearchRoomFilter;
import org.tanvir.model.Booking;
import org.tanvir.model.Room;
import org.tanvir.util.Util;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author tanvirtareq
 * @since 7/19/23
 */
@Service
public class RoomService {

    public final int ROOM_PER_PAGE = 5;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Room> searchRooms(SearchRoomFilter searchRoomFilter) {

        List<Room> rooms = getRoomList();

        List<Room> result = rooms.stream()
                .filter(room -> searchRoomFilter.getParkingFacility() == null
                        || room.getHotel().getParkingFacility().equals(searchRoomFilter.getParkingFacility()))

                .filter(room -> searchRoomFilter.getWifiFacility() == null
                        || room.getHotel().getWifiFacility().equals(searchRoomFilter.getWifiFacility()))

                .filter(room -> searchRoomFilter.getFitnessCentre() == null
                        || room.getHotel().getFitnessCentre().equals(searchRoomFilter.getFitnessCentre()))

                .filter(room -> searchRoomFilter.getSwimmingPool() == null
                        || room.getHotel().getSwimmingPool().equals(searchRoomFilter.getSwimmingPool()))

                .filter(room -> searchRoomFilter.getPriceMin() == null
                        || room.getPrice() >= searchRoomFilter.getPriceMin())

                .filter(room -> searchRoomFilter.getPriceMax() == null
                        || room.getPrice() <= searchRoomFilter.getPriceMax())

                .filter(room -> searchRoomFilter.getNumberOfBed() == null
                        || Objects.equals(room.getNumberOfBed(), searchRoomFilter.getNumberOfBed()))

                .filter(room -> searchRoomFilter.getRoomType() == null
                        || room.getType().equals(searchRoomFilter.getRoomType()))

                .filter(room -> searchRoomFilter.getHotelName() == null
                        || Util.partialMatch(room.getHotel().getName(), searchRoomFilter.getHotelName()))

                .filter(room -> searchRoomFilter.getLocation() == null
                        || Util.partialMatch(room.getHotel().getLocation(), searchRoomFilter.getLocation()))

                .filter(room -> availableRoom(room, searchRoomFilter.getCheckIn(), searchRoomFilter.getCheckOut()))

                .collect(Collectors.toList());

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

        return bookings.stream().noneMatch(booking -> isOverlap(checkIn, checkOut, booking.getCheckInDate(),
                booking.getCheckOutDate()));
    }

    public boolean isOverlap(LocalDate checkInSearch, LocalDate checkOutSearch, LocalDate checkInDate, LocalDate checkOutDate) {

        return (checkInSearch.isBefore(checkOutDate) || checkInSearch.isEqual(checkOutDate))
                && (checkOutSearch.isAfter(checkInDate) || checkOutSearch.isEqual(checkInDate));
    }

    private List<Booking> getBookings(String roomNumber, Room room) {

        List<Booking> bookings = room.getBookings().stream()
                .filter(booking -> booking.getRoomNumber().equals(roomNumber))
                .collect(Collectors.toList());

        return bookings;
    }

    public List<Room> getRoomList(Long currentPage) {

        int startIdx = (int) ((currentPage - 1) * ROOM_PER_PAGE);

        String jpql = "SELECT r FROM Room r ORDER BY r.id";

        return entityManager.createQuery(jpql, Room.class).setFirstResult(startIdx)
                .setMaxResults(ROOM_PER_PAGE).getResultList();

    }

    public List<Room> getRoomList() {
        String jpql = "SELECT r FROM Room r";

        return entityManager.createQuery(jpql, Room.class).getResultList();
    }

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
    public void update(Long roomId, String type, Long price, Integer numberOfBed, Set<String> roomNumbers) {

        Room room = entityManager.find(Room.class, roomId);
        room.setType(type);
        room.setPrice(price);
        room.setNumberOfBed(numberOfBed);
        room.setRoomNumbers(roomNumbers);

        entityManager.merge(room);
    }

    @Transactional
    public void merge(Room room) {
        entityManager.merge(room);
    }
}