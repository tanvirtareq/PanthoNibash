package net.therap.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tanvirtareq
 * @since 7/13/23
 */
@Entity
@Table
public class Room {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Hotel hotel;

    @NotNull(message = "Type can not be null")
    private String type;

    @NotNull
    @Min(value = 1, message = "Price must be a positive integer")
    private Long price;

    @ElementCollection
    @CollectionTable(name = "room_numbers", joinColumns = @JoinColumn(name = "room_id"))
    @Column(name = "room_number")
    private List<String> roomNumbers;

    @NotNull
    @Min(value = 1, message = "Number of bed must be a positive integer")
    private Long numberOfBed;

    @ElementCollection
    @CollectionTable(name = "room_images", joinColumns = @JoinColumn(name = "room_id"))
    @Lob
    @Column(name = "image")
    private List<byte[]> images;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<>();


    public void setHotel(Hotel hotel) {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<String> getRoomNumbers() {
        return roomNumbers;
    }

    public void setRoomNumbers(List<String> roomNumbers) {
        this.roomNumbers = roomNumbers;
    }

    public List<byte[]> getImages() {
        return images;
    }

    public void setImages(List<byte[]> images) {
        this.images = images;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
        booking.setRoom(this);
    }

    public void removeBooking(Booking booking) {
        bookings.remove(booking);
        booking.setRoom(null);
    }

    public Hotel getHotel() {
        return hotel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getNumberOfBed() {
        return numberOfBed;
    }

    public void setNumberOfBed(Long numberOfBed) {
        this.numberOfBed = numberOfBed;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
