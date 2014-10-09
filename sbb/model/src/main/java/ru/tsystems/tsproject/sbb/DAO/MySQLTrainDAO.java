package ru.tsystems.tsproject.sbb.DAO;

import ru.tsystems.tsproject.sbb.Entity.Train;

import javax.persistence.EntityManager;
import java.sql.Time;
import java.util.List;

/**
 * Created by apple on 04.10.14.
 */
public final class MySQLTrainDAO implements TrainDAO {
    private EntityManager entityManager;

    public MySQLTrainDAO(EntityManager em) {
        entityManager = em;
    }
    @Override
    public List<Train> getTrains(String aStation, String bStation, Time timeFrom, Time timeTo) {
        return (List<Train>) entityManager.createQuery("select trL.train from TimetableRecord trL join trL trR " +
                "where trR.station.name = :bStation " +
                "and trL.station.name = :aStation and trL.time >= :timeFrom and trL.time <= :timeTo")
                .setParameter("aStation", aStation)
                .setParameter("bStation", bStation)
                .setParameter("timeFrom", timeFrom)
                .setParameter("timeTo", timeTo);
    }

    @Override
    public List<Train> getAllTrains() {
        return entityManager.createQuery("select t from Train t", Train.class).getResultList();
    }

    @Override
    public void addTrain(int number, int seatsCount) {
        entityManager.getTransaction().begin();
        Train train = new Train(number, seatsCount);
        entityManager.persist(train);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteTrain(Train train) {
        entityManager.createQuery("delete from Train t where t.number = :number")
                     .setParameter("number", train.getTrainNumber())
                     .executeUpdate();

    }

    @Override
    public void updateTrain(Train train) {
        entityManager.createQuery("update Train t set t=:train where t.number=:number")
                     .setParameter("train", train)
                     .setParameter("number", train.getTrainNumber())
                     .executeUpdate();
    }

}
