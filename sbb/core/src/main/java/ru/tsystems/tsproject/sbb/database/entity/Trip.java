package ru.tsystems.tsproject.sbb.database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by apple on 14.10.14.
 */
@Entity
@NamedQuery(name="Trip.getTripsByRoute", query="select t from Trip t where t.route.id = :id and day(t.departureTime) = day(:date) " +
                                               "and month(t.departureTime) = month(:date) and year(t.departureTime) = year(:date)")
public class Trip {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private Date departureTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

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
