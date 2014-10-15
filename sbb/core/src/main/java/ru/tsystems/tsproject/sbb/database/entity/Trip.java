package ru.tsystems.tsproject.sbb.database.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 14.10.14.
 */
@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private Timestamp departureTime;

    @ManyToOne
    private Train train;

    @ManyToOne
    private Route route;

    @OneToMany(mappedBy = "trip")
    private List<Ticket> tickets;

    public Trip() {
        tickets = new ArrayList<Ticket>();
    }
}
