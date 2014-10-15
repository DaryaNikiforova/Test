package ru.tsystems.tsproject.sbb.database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 14.10.14.
 */
@Entity
public class Station {
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
