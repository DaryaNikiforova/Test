package ru.tsystems.tsproject.sbb.Entity;

import javax.persistence.*;

/**
 * Created by apple on 06.10.14.
 */
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String name;

    public Role() {
    }

    public void setRole(String role) {
        this.name=role;
    }
}
