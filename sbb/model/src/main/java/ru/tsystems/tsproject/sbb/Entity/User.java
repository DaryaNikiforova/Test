package ru.tsystems.tsproject.sbb.Entity;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by apple on 04.10.14.
 */

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"login", "password"})})
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String name;

    private String surname;

    @Past
    private Date birthDate;

    private String login;

    private String password;

    public User(String name, String surname, Date birthDate, String login, String password, Role role) {
        this();
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;


    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {
        role = new Role();
        tickets = new ArrayList<Ticket>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname=surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate=birthDate;
    }

    public String getLogin() { return login; }

    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }
}
