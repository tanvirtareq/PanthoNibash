package org.tanvir.model;

import org.tanvir.constants.PatternConstants;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Base64;

/**
 * @author tanvirtareq
 * @since 7/13/23
 */
@Entity
@Table(name = "booking")
public class Booking implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, updatable = false)
    @Size(max = 20)
    private String roomNumber;

    @ManyToOne
    @JoinColumn
    private Customer customer;

    @NotBlank(message = "{Name.can.not.be.blank}")
    @Column(updatable = false)
    @Size(max = 50, message = "{string.max.size}")
    private String guestName;

    @Email(message = "{Email.should.be.valid}")
    @NotBlank(message = "{Email.can.not.be.blank}")
    @Size(max = 50, message = "{string.max.size}")
    private String guestEmail;

    @Pattern(regexp = PatternConstants.PHONE_NUMBER_PATTERN, message = "{Phone.number.must.be.valid}")
    @NotBlank(message = "{Phone.number.can.not.be.blank}")
    private String guestPhoneNumber;

    @Lob
    @Column(name = "guest_image")
    private byte[] guestImage;

    @NotNull
    @DateTimeFormat(pattern = PatternConstants.DATE_FORMAT_PATTERN)
    private LocalDate checkInDate;

    @NotNull
    @DateTimeFormat(pattern = PatternConstants.DATE_FORMAT_PATTERN)
    private LocalDate checkOutDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Room room;

    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn
    private Review review;

    @Transient
    private String guestImageBase64Image;

    public Booking() {

    }

    public Booking(Customer customer) {
        this.setCustomer(customer);
        this.setGuestName(customer.getName());
        this.setGuestEmail(customer.getEmail());
        this.setGuestPhoneNumber(customer.getPhoneNumber());
    }

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