package net.therap.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * @author tanvirtareq
 * @since 7/13/23
 */
@Entity
@Table
public class Room {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @NotNull(message = "Type can not be null")
    private String type;

    @NotNull
    @Min(value = 1, message = "Price must be a positive integer")
    private Long price;

    @ElementCollection
    @CollectionTable(name = "room_numbers", joinColumns = @JoinColumn(name = "room_id"))
    @Column(name = "room_number")
    private List<String> roomNumbers = new ArrayList<>();

    @NotNull
    @Min(value = 1, message = "Number of bed must be a positive integer")
    private Long numberOfBed;

    private Long numberOfRoom;


    @Lob
    @Column(name = "room_image")
    private byte[] roomImage;


    @Transient
    private String roomImageBase64Image;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<>();

    public Long getNumberOfRoom() {
        setNumberOfRoom((long) roomNumbers.size());
        return numberOfRoom;
    }

    public void setNumberOfRoom(Long numberOfRoom) {
        this.numberOfRoom = numberOfRoom;
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

    public List<String> getRoomNumbers() {
        return roomNumbers;
    }

    public void setRoomNumbers(List<String> roomNumbers) {
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

    public Long getNumberOfBed() {
        return numberOfBed;
    }

    public void setNumberOfBed(Long numberOfBed) {
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
                ", numberOfRoom=" + numberOfRoom +
                '}';
    }
}
