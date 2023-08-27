package net.therap.model;

import net.therap.constants.PatternConstants;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static net.therap.constants.PatternConstants.PASSWORD_PATTERN;


/**
 * @author tanvirtareq
 * @since 7/13/23
 */
@Entity
@Table(name = "customer")
public class Customer implements Serializable {
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
    @Pattern(regexp = PASSWORD_PATTERN, message = "{Password.invalid.format}")
    private String password;

    @Pattern(regexp = PatternConstants.PHONE_NUMBER_PATTERN, message = "{Phone.number.must.be.valid}")
    private String phoneNumber;

    @NotNull(message = "{Date.of.Birth.can.not.be.null}")
    @DateTimeFormat(pattern = PatternConstants.DATE_FORMAT_PATTERN)
    private LocalDate dateOfBirth;

    @Lob
    @Column(name = "profile_picture")
    private byte[] profilePicture;

    @Transient
    private String profilePicBase64Image;

    @OneToMany(mappedBy = "customer", orphanRemoval = true)
    private List<Booking> bookings;

    public Customer() {
        this.bookings = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
        setProfilePicBase64Image(Base64.getEncoder().encodeToString(profilePicture));
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getProfilePicBase64Image() {
        setProfilePicBase64Image(Base64.getEncoder().encodeToString(this.getProfilePicture()));
        return profilePicBase64Image;
    }

    public void setProfilePicBase64Image(String profilePicBase64Image) {
        this.profilePicBase64Image = profilePicBase64Image;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", profilePicBase64Image='" + profilePicBase64Image + '\'' +
                ", bookings=" + bookings +
                '}';
    }
}
