package ru.tsystems.tsproject.sbb.database.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by apple on 14.10.14.
 */
@Entity
public class Service {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;

    private double value;

    public Service() {}

    @ManyToMany(mappedBy="services")
    private List<Ticket> tickets;
}
