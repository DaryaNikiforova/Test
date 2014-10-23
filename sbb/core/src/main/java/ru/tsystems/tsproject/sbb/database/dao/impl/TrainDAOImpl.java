package ru.tsystems.tsproject.sbb.database.dao.impl;

import ru.tsystems.tsproject.sbb.database.dao.TrainDAO;
import ru.tsystems.tsproject.sbb.database.entity.Rate;
import ru.tsystems.tsproject.sbb.database.entity.Train;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by apple on 04.10.14.
 */
public final class TrainDAOImpl implements TrainDAO {
    private EntityManager entityManager;

    public TrainDAOImpl(EntityManager em) {
        entityManager = em;
    }

    @Override
    public void addTrain(int number, int seatsCount, String name, int rateId) {
        Train train = new Train(number, seatsCount, name, new Rate(rateId));
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(train);
            entityManager.getTransaction().commit();
        }
        finally {
            if (entityManager.getTransaction().isActive())
                entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void deleteTrain(Train train) {
        entityManager.createQuery("delete from Train t where t.id = :number")
                .setParameter("number", train.getId())
                .executeUpdate();
    }

    /*@Override
    public List<Train> getTrains(String aStation, String bStation, Timestamp timeFrom, Timestamp timeTo) {
        return (List<Train>) entityManager.createQuery("select trL.train from TimetableRecord trL join trL trR " +
                "where trR.station.name = :bStation " +
                "and trL.station.name = :aStation and trL.time >= :timeFrom and trL.time <= :timeTo")
                .setParameter("aStation", aStation)
                .setParameter("bStation", bStation)
                .setParameter("timeFrom", timeFrom)
                .setParameter("timeTo", timeTo);
    }*/

    @Override
    public List<Train> getAllTrains() {
        return entityManager.createQuery("select t from Train t", Train.class).getResultList();
    }


    @Override
    public void updateTrain(Train train) {
        entityManager.createQuery("update Train t set t=:train where t.id=:number")
                     .setParameter("train", train)
                     .setParameter("number", train.getId())
                     .executeUpdate();
    }

    @Override
    public List<Train> getTrains() {
        return entityManager.createQuery("select t from Train t").getResultList();
    }

}
