package ru.tsystems.tsproject.sbb.database.dao;

import ru.tsystems.tsproject.sbb.database.entity.Train;

import java.util.List;

/**
 * Created by apple on 14.10.14.
 */
public interface TrainDAO {
    //List<Train> getTrains (String aStation, String bStation, Timestamp timeFrom, Timestamp timeTo);
    List<Train> getAllTrains();
    void addTrain(int number, int seatsCount);
    void deleteTrain(Train train);
    void updateTrain(Train train);
}
