package ru.tsystems.tsproject.sbb.DAO;

import com.sun.tools.javac.util.List;
import ru.tsystems.tsproject.sbb.Entity.Ticket;
import ru.tsystems.tsproject.sbb.Entity.Train;
import ru.tsystems.tsproject.sbb.Entity.User;

import javax.persistence.EntityManager;
import java.util.Date;

/**
 * Created by apple on 04.10.14.
 */
public final class MySQLTicketDAO implements TicketDAO {
    private EntityManager entityManager;

    public MySQLTicketDAO(EntityManager em) {
        entityManager = em;
    }
    @Override
    public void addTicket(Train train, User user, Date date) {
        entityManager.getTransaction().begin();
        Ticket ticket = new Ticket(train, user, date);
        entityManager.persist(ticket);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<User> getPassengers(int trainId, Date date) {
        return (List<User>) entityManager.createQuery("select t.user from Ticket t where t.date = :date and t.id = :trainId")
                                         .setParameter("date", date)
                                         .setParameter("trainId", trainId)
                                         .getResultList();
    }

    @Override
    public boolean isUserRegistered(User user) {
        Integer result = (Integer) entityManager.createQuery("select count(t.id) from Ticket t " +
                                                             "where t.user.name=:name and t.user.surname = :surname " +
                                                             "and t.user.password=:password")
                                                .setParameter("name", user.getName())
                                                .setParameter("surname", user.getSurname())
                                                .setParameter("password", user.getPassword())
                                                .getSingleResult();
        return result>0;
    }

}
