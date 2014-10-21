package ru.tsystems.tsproject.sbb.database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 14.10.14.
 */
@Entity
public class Route {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public List<RouteEntry> getRouteEntries() {
        return routeEntries;
    }

    public void setRouteEntries(List<RouteEntry> routeEntries) {
        this.routeEntries = routeEntries;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public Route() {
        trips = new ArrayList<Trip>();
        routeEntries = new ArrayList<RouteEntry>();
    }

    @OneToMany(mappedBy = "route")
    List<Trip> trips;

    @OneToMany(mappedBy = "route", cascade = {CascadeType.PERSIST})
    List<RouteEntry> routeEntries;
}
