package ru.tsystems.tsproject.sbb.services;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.database.dao.FactoryDAO;
import ru.tsystems.tsproject.sbb.database.dao.StationDAO;
import ru.tsystems.tsproject.sbb.database.entity.Station;
import ru.tsystems.tsproject.sbb.services.exceptions.ServiceException;
import ru.tsystems.tsproject.sbb.services.exceptions.StationAlreadyExistException;
import ru.tsystems.tsproject.sbb.transferObjects.StationTO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements methods interacting with DAO methods. Needs to get and change data in database.
 * @author Daria Nikiforova
 */
public class StationService {
    private static final Logger LOGGER = Logger.getLogger(Station.class);

    EntityManager entityManager;
    StationDAO stationDAO;

    /**
     * Creates DAO factory and DAO instance for working with Station entity
     */
    public StationService(EntityManager entityManager, StationDAO stationDAO) {
        this.entityManager = entityManager;
        this.stationDAO = stationDAO;
    }

    public StationService(EntityManager entityManager, FactoryDAO factoryDAO) {
        this.entityManager = entityManager;
        stationDAO = factoryDAO.getStationDAO();
    }

    /**
     * Adds station to database. Throws PersistenceException if database connection is lost,
     * bad request or error with transaction. Throws StationAlreadyExistsException when the same
     * record already exists in database.
     * @param stationTO added to database
     * @throws ServiceException
     */
    public void addStation(StationTO stationTO) throws ServiceException {
        try {
            if (!stationDAO.isStationExist(stationTO.getName())) {
                entityManager.getTransaction().begin();
                Station station = new Station();
                station.setName(stationTO.getName());
                stationDAO.addStation(station);
                entityManager.getTransaction().commit();
                LOGGER.info("Добавление станции в базу данных");
                stationDAO.addStation(station);
            }
            else {
                LOGGER.error("Станция уже существует в базе данных");
                throw new StationAlreadyExistException("Станция уже существует в базе данных");
            }

        } catch (PersistenceException ex) {
            LOGGER.error("Ошибка при добавлении станции в базу данных");
            throw new ServiceException("Ошибка при добавлении станции в базу данных");
        }
        catch (NullPointerException ex) {
            LOGGER.error("Попытка некорректного ввода в поле данных");
            throw new NullPointerException("Попытка некорректного ввода в поле данных");
        }
        finally {
            if (entityManager.getTransaction().isActive())
                entityManager.getTransaction().rollback();
        }
    }

    /**
     * Return list of all stations stored in database. Throws exception if database connection is lost,
     * bad request or error with transaction.
     * @return list of stations.
     * @throws ServiceException
     */
    public List<StationTO> getStations() throws ServiceException{
        List<String> stationNames = new ArrayList<String>();
        LOGGER.info("Ошибка при получении списка станций");
        try {
             stationNames = stationDAO.getStations();
        } catch (PersistenceException ex) {
            LOGGER.error("Ошибка при получении списка станций");
            throw new ServiceException("Ошибка при получении списка станций");
        }
        List<StationTO> stations = new ArrayList<StationTO>();
        for (String name : stationNames) {
            stations.add(new StationTO(name));
        }
        return  stations;
    }
}
