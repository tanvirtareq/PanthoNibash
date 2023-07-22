package net.therap.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Base64;

/**
 * @author tanvirtareq
 * @since 7/13/23
 */
@Entity
@Table
public class Booking {
    @Id
    @GeneratedValue
    private Long id;

    private String roomNumber;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @NotBlank(message = "Name can't be blank")
    private String guestName;

    @Email(message = "Email should be a valid email address")
    @NotBlank(message = "Email can't be blank")
    private String guestEmail;

    @Pattern(regexp = "\\d{11}", message = "Phone number must be a 11-digit number")
    @NotBlank(message = "Phone number can't be blank")
    private String guestPhoneNumber;

    @Lob
    @Column(name = "guest_image")
    private byte[] guestImage;

    @Transient
    private String guestImageBase64Image;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkInDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkOutDate;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "review_id")
    private Review review;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
        review.setBooking(this);
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    public String getGuestPhoneNumber() {
        return guestPhoneNumber;
    }

    public void setGuestPhoneNumber(String guestPhoneNumber) {
        this.guestPhoneNumber = guestPhoneNumber;
    }

    public byte[] getGuestImage() {
        return guestImage;
    }

    public void setGuestImage(byte[] guestImage) {
        this.guestImage = guestImage;
    }

    public String getGuestImageBase64Image() {
        this.setGuestImageBase64Image(Base64.getEncoder().encodeToString(this.guestImage));
        return guestImageBase64Image;
    }

    public void setGuestImageBase64Image(String guestImageBase64Image) {
        this.guestImageBase64Image = guestImageBase64Image;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", roomNumber='" + roomNumber + '\'' +
                ", guestName='" + guestName + '\'' +
                ", guestEmail='" + guestEmail + '\'' +
                ", guestPhoneNumber='" + guestPhoneNumber + '\'' +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }
}
