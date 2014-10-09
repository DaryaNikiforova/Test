package ru.tsystems.tsproject.sbb.Factory;

import ru.tsystems.tsproject.sbb.DAO.*;

/**
 * Created by apple on 04.10.14.
 */
public interface DAOFactory {
    UserDAO getUserDAO();
    TrainDAO getTrainDAO();
    TicketDAO getTicketDAO();
    TimetableDAO getTimetableDAO();
    StationDAO getStationDAO();
}
