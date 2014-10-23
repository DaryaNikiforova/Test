package ru.tsystems.tsproject.sbb.database.entity;

import javax.persistence.*;

/**
 * Created by apple on 14.10.14.
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Rate {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String name;

    private double value;

    private boolean forTrain;


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


    public boolean isForTrain() {
        return forTrain;
    }

    public void setForTrain(boolean forTrain) {
        this.forTrain = forTrain;
    }

    public Rate() {}

    public Rate(int id) {
        this.id = id;
    }
}
