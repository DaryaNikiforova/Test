package ru.tsystems.tsproject.sbb.database.dao.impl;

import ru.tsystems.tsproject.sbb.database.dao.TripDAO;
import ru.tsystems.tsproject.sbb.database.entity.Trip;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

/**
 * Created by apple on 16.10.14.
 */
public final class TripDAOImpl implements TripDAO {
    EntityManager entityManager;

    public TripDAOImpl(EntityManager em) {
        entityManager = em;
    }

    @Override
    public List<Trip> getTripsByInterval(int routeId, Date timeFrom, Date timeTo) {
         return entityManager.createQuery("select t from Trip t where t.route.id = :id " +
                                   "and t.departureTime >= :timeFrom and t.departureTime <= :timeTo")
                             .setParameter("id", routeId)
                             .setParameter("timeFrom", timeFrom)
                             .setParameter("timeTo", timeTo)
                             .getResultList();
    }

    public List<Trip> getTripsByRoute(int routeId, Date depDate) {
        return entityManager.createNamedQuery("Trip.getTripsByRoute", Trip.class)
                            .setParameter("id", routeId)
                            .setParameter("date", depDate).getResultList();
    }

    @Override
    public Trip getTrip(int tripId) {
        return (Trip) entityManager.createQuery("select t from Trip t where t.id = :id")
                                   .setParameter("id", tripId).getSingleResult();
    }

    @Override
    public List<Trip> getAllTrips() {
        return entityManager.createQuery("select t from Trip t").getResultList();
    }
}
