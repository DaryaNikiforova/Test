package ru.tsystems.tsproject.sbb.database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 14.10.14.
 */
@Entity
public class Station {
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

    public List<RouteEntry> getRouteEntries() {
        return routeEntries;
    }

    public void setRouteEntries(List<RouteEntry> routeEntries) {
        this.routeEntries = routeEntries;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy="station")
    List<RouteEntry> routeEntries;

    public Station() {
        routeEntries = new ArrayList<RouteEntry>();
    }

    public Station(String name) {
        this.name = name;
    }
}
