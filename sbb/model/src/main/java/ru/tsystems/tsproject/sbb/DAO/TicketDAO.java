package ru.tsystems.tsproject.sbb.DAO;

import ru.tsystems.tsproject.sbb.Entity.Train;
import ru.tsystems.tsproject.sbb.Entity.User;

import java.util.Date;
import java.util.List;

/**
 * Created by apple on 04.10.14.
 */
public interface TicketDAO {
    void addTicket(Train train, User user, Date date);
    List<User> getPassengers(int trainId, Date date);
    boolean isUserRegistered(User user);
}
