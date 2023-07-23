package net.therap.model;

import javax.persistence.*;

@Entity
@Table
public class Rating {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    private double rating;

    private double weight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}

