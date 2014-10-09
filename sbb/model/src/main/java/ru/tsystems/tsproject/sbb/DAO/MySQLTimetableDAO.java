package ru.tsystems.tsproject.sbb.DAO;

import ru.tsystems.tsproject.sbb.Entity.Station;
import ru.tsystems.tsproject.sbb.Entity.TimetableRecord;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by apple on 04.10.14.
 */
public final class MySQLTimetableDAO implements TimetableDAO {
    private EntityManager entityManager;

    public MySQLTimetableDAO (EntityManager em) {
        entityManager=em;
    }

    @Override
    public void addTimetableRecord(TimetableRecord record) {
        entityManager.getTransaction().begin();
        entityManager.persist(record);
        entityManager.getTransaction().commit();
    }

    @Override
    public Integer getRemainingTime(int trainId, String station) {
        return (Integer) entityManager.createQuery("select t.time - current_timestamp() from TimetableRecord t " +
                "                                   where  t.train.number = :train and t.station.id = :station")
                                      .setParameter("train", trainId)
                                      .setParameter("station", station).getSingleResult();
    }

    @Override
    public void updateTimetableRecord(TimetableRecord timetable) {
        entityManager.createQuery("update TimetableRecord t set t=:timetable where t.id=:number")
                                   .setParameter("timetable", timetable)
                                   .setParameter("number", timetable.getId())
                                   .executeUpdate();
    }

    @Override
    public void deleteTimetableRecord(TimetableRecord timetable) {
        entityManager.createQuery("delete from TimetableRecord t where t.id = :number")
                     .setParameter("number", timetable.getId())
                     .executeUpdate();
    }

    @Override
    public List<TimetableRecord> getTimetableByStation(Station station) {
        return entityManager.find(Station.class, station.getId()).getTimetables();
    }
}