package ru.tsystems.tsproject.sbb.database.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by apple on 14.10.14.
 */
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private Timestamp date;

    private int seat;

    private int price;

    @ManyToOne
    private User user;

    @ManyToOne
    private Station stationTo;

    @ManyToOne
    private Station stationFrom;

    @ManyToOne
    private Trip trip;

    public Ticket() {}
}
