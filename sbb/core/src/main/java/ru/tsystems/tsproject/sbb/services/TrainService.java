package ru.tsystems.tsproject.sbb.services;

import ru.tsystems.tsproject.sbb.database.dao.RateDAO;
import ru.tsystems.tsproject.sbb.database.dao.TrainDAO;
import ru.tsystems.tsproject.sbb.database.dao.impl.FactoryDAOImpl;
import ru.tsystems.tsproject.sbb.database.entity.Rate;
import ru.tsystems.tsproject.sbb.database.entity.Train;
import ru.tsystems.tsproject.sbb.transferObjects.RateTO;
import ru.tsystems.tsproject.sbb.transferObjects.TrainTO;

import java.util.ArrayList;
import java.util.List;

public class TrainService {
    FactoryDAOImpl factory = new FactoryDAOImpl();
    TrainDAO trainDAO = factory.getTrainDAO();
    RateDAO rateDAO = factory.getRateDAO();

    public List<RateTO> getTrainRates() {
        List<Rate> rates = rateDAO.getTrainRates();
        List<RateTO> result = new ArrayList<RateTO>();
        for (Rate rate : rates) {
            RateTO rateTO = new RateTO();
            rateTO.setId(rate.getId());
            rateTO.setName(rate.getName());
            result.add(rateTO);
        }
        return result;
    }

    public void addTrain(TrainTO train) {
        trainDAO.addTrain(train.getNumber(), train.getSeatCount(), train.getName(), train.getRateId());
    }

    public List<TrainTO> getTrains() {
        List<Train> list = trainDAO.getTrains();
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
