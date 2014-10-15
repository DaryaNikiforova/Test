package ru.tsystems.tsproject.sbb.database.entity;

import javax.persistence.*;
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

    @Id
    private int id;

    private int seatCount;

    @OneToMany(mappedBy = "train")
    private List<Trip> trips;

    public Train() {
        trips = new ArrayList<Trip>();
    }

    public Train(int id, int seatCount) {
        this.seatCount = seatCount;
    }

}
