package ru.tsystems.tsproject.sbb.database.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 14.10.14.
 */
@Entity
public class Train {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    @Id
    private int id;

    private int seatCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;



    @OneToOne
    private Rate rate;

    @OneToMany(mappedBy = "train")
    private List<Trip> trips;

    public Train() {
        trips = new ArrayList<Trip>();
    }

    public Train(int id, int seatCount, String name, Rate rate) {
        this.seatCount = seatCount;
        this.id = id;
        this.name = name;
        this.rate = rate;
    }


    public Rate getRate() {
        return rate;
    }

}
