package net.therap.model;

import net.therap.constants.PatternConstants;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * @author tanvirtareq
 * @since 7/13/23
 */
@Entity
@Table(name = "hotel")
public class Hotel implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "{Name.can.not.be.blank}")
    @Size(max = 50, message = "{string.max.size}")
    private String name;

    @Email(message = "{Email.should.be.valid}")
    @Column(unique = true, updatable = false)
    @Size(max = 50, message = "{string.max.size}")
    private String email;

    @NotBlank(message = "{Password.cannot.be.blank}")
    @Size(min = 3, max = 200, message = "{Password.length.invalid}")
    @Pattern(regexp = PatternConstants.PASSWORD_PATTERN, message = "{Password.invalid.format}")
    private String password;

    @Pattern(regexp = PatternConstants.PHONE_NUMBER_PATTERN, message = "{Phone.number.must.be.valid}")
    private String phoneNumber;

    @NotNull(message = "{Location.can.not.be.null}")
    @Size(max = 50, message = "{string.max.size}")
    private String location;

    @NotNull(message = "{Parking.Facility.can.not.be.null}")
    @Size(max = 10, message = "{string.max.size}")
    private String parkingFacility;

    @NotNull(message = "{Swimming.Pool.can.not.be.null}")
    @Size(max = 10, message = "{string.max.size}")
    private String swimmingPool;

    @NotNull(message = "{Fitness.Centre.can.not.be.null}")
    @Size(max = 10, message = "{string.max.size}")
    private String fitnessCentre;

    @NotNull(message = "{Wifi.Facility.can.not.be.null}")
    @Size(max = 10, message = "{string.max.size}")
    private String wifiFacility;

    @Lob
    @Column(name = "hotel_image")
    private byte[] hotelImage;

    @Transient
    private String hotelImageBase64Image;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> rooms;

    public Hotel() {
        this.rooms= new ArrayList<>();
    }

    @OneToOne(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn
    private Rating rating;

    public byte[] getHotelImage() {
        return hotelImage;
    }

    public void setHotelImage(byte[] hotelImage) {
        this.hotelImage = hotelImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
        room.setHotel(this);
    }

    public void removeRoom(Room room) {
        rooms.remove(room);
        room.setHotel(null);
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
        rating.setHotel(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getParkingFacility() {
        return parkingFacility;
    }

    public void setParkingFacility(String parkingFacility) {
        this.parkingFacility = parkingFacility;
    }

    public String getSwimmingPool() {
        return swimmingPool;
    }

    public void setSwimmingPool(String swimmingPool) {
        this.swimmingPool = swimmingPool;
    }

    public String getFitnessCentre() {
        return fitnessCentre;
    }

    public void setFitnessCentre(String fitnessCentre) {
        this.fitnessCentre = fitnessCentre;
    }

    public String getWifiFacility() {
        return wifiFacility;
    }

    public void setWifiFacility(String wifiFacility) {
        this.wifiFacility = wifiFacility;
    }

    public String getHotelImageBase64Image() {
        setHotelImageBase64Image(Base64.getEncoder().encodeToString(this.getHotelImage()));
        return hotelImageBase64Image;
    }

    public void setHotelImageBase64Image(String hotelImageBase64Image) {
        this.hotelImageBase64Image = hotelImageBase64Image;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", location='" + location + '\'' +
                ", parkingFacility='" + parkingFacility + '\'' +
                ", swimmingPool='" + swimmingPool + '\'' +
                ", fitnessCentre='" + fitnessCentre + '\'' +
                ", wifiFacility='" + wifiFacility + '\'' +
                '}';
    }
}