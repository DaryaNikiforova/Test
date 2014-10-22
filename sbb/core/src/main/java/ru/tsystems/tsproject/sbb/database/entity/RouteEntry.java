package ru.tsystems.tsproject.sbb.database.entity;

import javax.persistence.*;

/**
 * Created by apple on 14.10.14.
 */
@Entity
public class RouteEntry {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getSeqNumber() {
        return seqNumber;
    }

    public void setSeqNumber(int seqNumber) {
        this.seqNumber = seqNumber;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    private double distance;

    private int seqNumber;

    private int hour;

    private int minute;

    @ManyToOne
    private Station station;

    @ManyToOne
    private Route route;

    public RouteEntry() {}
}
