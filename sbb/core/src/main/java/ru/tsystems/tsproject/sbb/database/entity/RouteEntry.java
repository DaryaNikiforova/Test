package ru.tsystems.tsproject.sbb.database.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by apple on 14.10.14.
 */
@Entity
public class RouteEntry {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private Timestamp time;

    private int distance;

    private int seqNumber;

    @ManyToOne
    private Station station;

    @ManyToOne
    private Route route;

    public RouteEntry() {}
}
