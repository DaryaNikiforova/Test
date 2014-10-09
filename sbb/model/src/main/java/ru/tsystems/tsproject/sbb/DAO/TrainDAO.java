package ru.tsystems.tsproject.sbb.DAO;

import ru.tsystems.tsproject.sbb.Entity.Train;

import java.sql.Time;
import java.util.List;

/**
 * Created by apple on 04.10.14.
 */
public interface TrainDAO {
    List<Train> getTrains (String aStation, String bStation, Time timeFrom, Time timeTo);
    List<Train> getAllTrains();
    void addTrain(int number, int seatsCount);
    void deleteTrain(Train train);
    void updateTrain(Train train);
}
