package net.therap.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tanvirtareq
 * @since 7/13/23
 */
@Entity
@Table
public class Hotel {
    @Id
    private Long id;

    @NotBlank(message = "Name can't be blank")
    private String name;

    @Email(message = "Email should be a valid email address")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 2, max = 20, message = "Password must be between 2 and 20 characters long")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).*$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, and one digit")
    private String password;

    @Pattern(regexp = "\\d{11}", message = "Phone number must be a 11-digit number")
    private String phoneNumber;

    @NotBlank(message = "Location can not be null")
    private String location;

    @NotNull(message = "Parking Facility can not be null")
    private String parkingFacility;


    @NotNull(message = "Swimming pool can not be null")
    private String swimmingPool;


    @NotNull(message = "Fitness centre can not be null")
    private String fitnessCentre;


    @NotNull(message = "Wifi Facility can not be null")
    private String wifiFacility;

    @ElementCollection
    @CollectionTable(name = "hotel_images", joinColumns = @JoinColumn(name = "hotel_id"))
    @Lob
    @Column(name = "image")
    private List<byte[]> images;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;


    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> rooms = new ArrayList<>();

    @OneToOne(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private Rating rating;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<byte[]> getImages() {
        return images;
    }

    public void setImages(List<byte[]> images) {
        this.images = images;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<Room> getRooms() {
        return rooms;
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

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
