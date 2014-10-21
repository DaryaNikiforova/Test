package ru.tsystems.tsproject.sbb.database.dao;

import ru.tsystems.tsproject.sbb.database.entity.Station;

import java.util.List;

/**
 * Created by apple on 14.10.14.
 */
public interface StationDAO {
    void addStation(String name);
    Station getStation (String name);
    List<String> getStations();
}
