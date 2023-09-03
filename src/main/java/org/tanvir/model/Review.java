package org.tanvir.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author tanvirtareq
 * @since 7/13/23
 */
@Entity
@Table(name = "review")
public class Review implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Review cannot be blank")
    @Column(updatable = false)
    @Size(max = 500, message = "{string.max.size}")
    private String description;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    @Column(updatable = false)
    private int rating;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Booking booking;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String review) {
        this.description = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", review='" + description + '\'' +
                ", rating=" + rating +
                '}';
    }
}
