package ru.tsystems.tsproject.sbb.database.dao.impl;

import ru.tsystems.tsproject.sbb.database.dao.TicketDAO;
import ru.tsystems.tsproject.sbb.database.entity.Ticket;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by apple on 04.10.14.
 */
public final class TicketDAOImpl implements TicketDAO {
    private EntityManager entityManager;

    public TicketDAOImpl(EntityManager em) {
        entityManager = em;
    }

    @Override
    public void addTicket(Ticket ticket) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(ticket);
            entityManager.getTransaction().commit();
        }
        finally {
            if (entityManager.getTransaction().isActive())
                entityManager.getTransaction().rollback();
        }
    }

    @Override
    public List<Ticket> getBoughtTickets(String stationFrom, String stationTo, int tripId) {
        return entityManager.createQuery("select t.seat from Ticket t where t.trip.id = :id")
                            .setParameter("id", tripId).getResultList();
    }


    //@Override
   /* public List<User> getPassengers(int trainId, Date date) {
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
    }*/

}
