package ru.tsystems.tsproject.sbb.database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 14.10.14.
 */
@Entity
public class Route {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String name;

    public Route() {
        trips = new ArrayList<Trip>();
        routeEntries = new ArrayList<RouteEntry>();
    }

    @OneToMany(mappedBy = "route")
    List<Trip> trips;

    @OneToMany(mappedBy = "route")
    List<RouteEntry> routeEntries;
}
