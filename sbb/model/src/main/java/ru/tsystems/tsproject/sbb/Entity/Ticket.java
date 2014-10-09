package ru.tsystems.tsproject.sbb.Entity;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.util.Date;

/**
 * Created by apple on 04.10.14.
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"train_number", "user_id", "date"})})
public class Ticket {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Train train;

    @ManyToOne
    private User user;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Future
    private Date date;

    public Ticket() {
    }

    public Ticket(Train train, User user, Date date) {
        this.train = train;
        this.user = user;
        this.date = date;
    }

    public Train getTrain() {
        return train;
    }

    public User getUser() {
        return user;
    }

    public void setTrain(Train train) {
        this.train=train;
    }

    public void setUser(User user) {
        this.user=user;
    }
}
