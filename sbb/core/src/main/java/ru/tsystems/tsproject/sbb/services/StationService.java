package ru.tsystems.tsproject.sbb.services;

import ru.tsystems.tsproject.sbb.database.dao.StationDAO;
import ru.tsystems.tsproject.sbb.database.dao.impl.FactoryDAOImpl;
import ru.tsystems.tsproject.sbb.transferObjects.StationTO;

import java.util.ArrayList;
import java.util.List;

public class StationService {
    FactoryDAOImpl factory = new FactoryDAOImpl();
    StationDAO stationDAO = factory.getStationDAO();

    public void addStation(StationTO station) {
        stationDAO.addStation(station.getName());
    }
    public List<StationTO> getStations() {
        List<String> stationNames = stationDAO.getStations();
        List<StationTO> stations = new ArrayList<StationTO>();
        for (String name : stationNames) {
            stations.add(new StationTO(name));
        }
        return  stations;
    }
}
