package ru.tsystems.tsproject.sbb.DAO;

import ru.tsystems.tsproject.sbb.Entity.Station;
import ru.tsystems.tsproject.sbb.Entity.TimetableRecord;

import java.util.List;

/**
 * Created by apple on 04.10.14.
 */
public interface TimetableDAO {
    void addTimetableRecord(TimetableRecord timetable);
    Integer getRemainingTime(int trainId, String station);
    void updateTimetableRecord(TimetableRecord timetable);
    void deleteTimetableRecord(TimetableRecord timetable);
    List<TimetableRecord> getTimetableByStation(Station station);
}
