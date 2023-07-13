package net.therap.model;

import javax.persistence.*;

/**
 * @author tanvirtareq
 * @since 7/13/23
 */
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Rating {
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    private double rating;

    private double weight;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

}

