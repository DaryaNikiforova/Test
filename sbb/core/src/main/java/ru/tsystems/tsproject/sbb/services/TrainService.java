package ru.tsystems.tsproject.sbb.services;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.database.dao.FactoryDAO;
import ru.tsystems.tsproject.sbb.database.dao.RateDAO;
import ru.tsystems.tsproject.sbb.database.dao.TrainDAO;
import ru.tsystems.tsproject.sbb.database.dao.impl.FactoryDAOImpl;
import ru.tsystems.tsproject.sbb.database.entity.Rate;
import ru.tsystems.tsproject.sbb.database.entity.Train;
import ru.tsystems.tsproject.sbb.services.exceptions.ServiceException;
import ru.tsystems.tsproject.sbb.services.exceptions.TrainAlreadyExistException;
import ru.tsystems.tsproject.sbb.transferObjects.RateTO;
import ru.tsystems.tsproject.sbb.transferObjects.TrainTO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements methods interacting with DAO methods. Needs to get and change data in database.
 * @author Daria Nikiforova
 */
public class TrainService {
    private static final Logger LOGGER = Logger.getLogger(TrainService.class);

    EntityManager entityManager;
    TrainDAO trainDAO;
    RateDAO rateDAO;

    public TrainService(EntityManager entityManager, TrainDAO trainDAO, RateDAO rateDAO) {
        this.entityManager = entityManager;
        this.trainDAO = trainDAO;
        this.rateDAO = rateDAO;
    }

    /**
     * Creates DAO factory and DAO instances for working with Train, Rate.
     */

    public TrainService(EntityManager entityManager, FactoryDAO factoryDAO) {
        this.entityManager = entityManager;
        trainDAO = factoryDAO.getTrainDAO();
        rateDAO = factoryDAO.getRateDAO();
    }

    /**
     * Returns all rates that specified for trains (express, speedy, etc.). Throws exception
     * if database connection is lost, bad request or error with transaction.
     * @return
     * @throws ServiceException
     */
    public List<RateTO> getTrainRates() throws ServiceException {
        List<Rate> rates = new ArrayList<Rate>();
        LOGGER.info("Получение типов поездов");
        try {
            rates = rateDAO.getTrainRates();
        } catch (PersistenceException ex) {
            LOGGER.error("Ошибка при получении информации о маршрутах");
            throw new ServiceException("Ошибка при получении информации о маршрутах");
        }
        List<RateTO> result = new ArrayList<RateTO>();
            for (Rate rate : rates) {
                RateTO rateTO = new RateTO();
                rateTO.setId(rate.getId());
                rateTO.setName(rate.getName());
                result.add(rateTO);
            }
        return result;
    }

    /**
     * Adds train to database. Throws exception if database connection is lost,
     * bad request or error with transaction. Throws TrainAlreadyExistException if trying
     * to add train that already exists into database.
     * @param train train that want to add
     * @throws ServiceException
     */
    public void addTrain(TrainTO trainTO) throws ServiceException
    {
        try {
            if (!trainDAO.isTrainExist(trainTO.getNumber())) {
                entityManager.getTransaction().begin();
                Train train = new Train(trainTO.getNumber(), trainTO.getSeatCount(), trainTO.getName(), new Rate(trainTO.getRateId()));
                trainDAO.addTrain(train);
                entityManager.getTransaction().commit();
                LOGGER.info("Поезд добавлен в базу данных");
            }
            else {
                LOGGER.error("Поезд с таким номером уже существует в базе данных");
                throw new TrainAlreadyExistException("Поезд с таким номером уже существует в базе данных");
            }
        } catch (PersistenceException e) {
            LOGGER.error("Невозможно добавить поезд в базу данных");
            throw new ServiceException("Невозможно добавить поезд в базу данных");
        }
        finally {
            if (entityManager.getTransaction().isActive())
                entityManager.getTransaction().rollback();
        }
    }
    /**
     * Returns list of all trains. Throws exception if database connection is lost,
     * bad request or error with transaction.
     * @return list of trains
     * @throws ServiceException
     */
    public List<TrainTO> getTrains() throws ServiceException {
        List<Train> list = new ArrayList<Train>();
        LOGGER.info("Получение списка поездов");
        try {
            list = trainDAO.getTrains();
        } catch(PersistenceException ex) {
            LOGGER.error("Ошибка при получении информации о поездах");
            throw new ServiceException("Ошибка о получении информации о поездах");
        }
        List<TrainTO> result = new ArrayList<TrainTO>();
        for (Train t:list) {
            TrainTO train = new TrainTO();
            train.setNumber(t.getId());
            train.setName(t.getName());
            train.setSeatCount(t.getSeatCount());
            train.setRateName(t.getRate().getName());
            train.setRateId(t.getRate().getId());
            result.add(train);
        }
        return result;
    }
}
