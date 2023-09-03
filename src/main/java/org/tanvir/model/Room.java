package org.tanvir.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

/**
 * @author tanvirtareq
 * @since 7/13/23
 */
@Entity
@Table(name = "room")
public class Room implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Hotel hotel;

    @NotNull(message = "Type can not be null")
    @Size(max = 10, message = "{string.max.size}")
    private String type;

    @NotNull
    @Min(value = 1, message = "Price must be a positive integer")
    private Long price;

    @ElementCollection
    @CollectionTable(name = "room_numbers", joinColumns = @JoinColumn(name = "room_id"))
    @Column(name = "room_number")
    private Set<String> roomNumbers;

    @NotNull
    @Min(value = 1, message = "Number of bed must be a positive integer")
    @Max(value = 5, message = "{maximum.number}")
    private Integer numberOfBed;

    @Lob
    @Column(name = "room_image")
    private byte[] roomImage;

    @Transient
    private String roomImageBase64Image;

    @OneToMany(mappedBy = "room", orphanRemoval = true)
    private List<Booking> bookings;

    public Room() {
        this.bookings = new ArrayList<>();
        this.roomNumbers = new HashSet<>();
    }

    public byte[] getRoomImage() {
        return roomImage;
    }

    public void setRoomImage(byte[] roomImage) {
        this.roomImage = roomImage;
    }

    public String getRoomImageBase64Image() {
        setRoomImageBase64Image(Base64.getEncoder().encodeToString(this.getRoomImage()));
        return roomImageBase64Image;
    }

    public void setRoomImageBase64Image(String roomImageBase64Image) {
        this.roomImageBase64Image = roomImageBase64Image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<String> getRoomNumbers() {
        return roomNumbers;
    }

    public void setRoomNumbers(Set<String> roomNumbers) {
        this.roomNumbers = roomNumbers;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
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

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
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

    public Integer getNumberOfBed() {
        return numberOfBed;
    }

    public void setNumberOfBed(Integer numberOfBed) {
        this.numberOfBed = numberOfBed;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", roomNumbers=" + roomNumbers +
                ", numberOfBed=" + numberOfBed +
                '}';
    }
}