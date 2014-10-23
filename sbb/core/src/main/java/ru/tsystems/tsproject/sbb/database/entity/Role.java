package ru.tsystems.tsproject.sbb.database.entity;

import javax.persistence.*;

/**
 * Created by apple on 14.10.14.
 */
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String name;

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


    public Role() {}
}
