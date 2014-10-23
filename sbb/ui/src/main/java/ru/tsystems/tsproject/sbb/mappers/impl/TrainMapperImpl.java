package ru.tsystems.tsproject.sbb.mappers.impl;

import ru.tsystems.tsproject.sbb.mappers.Mapper;
import ru.tsystems.tsproject.sbb.transferObjects.TrainTO;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by apple on 22.10.2014.
 */
public class TrainMapperImpl implements Mapper<TrainTO> {

    @Override
    public TrainTO map(HttpServletRequest request) {
        TrainTO train = new TrainTO();
        train.setNumber(Integer.parseInt(request.getParameter("number")));
        train.setName(request.getParameter("name"));
        train.setSeatCount(Integer.parseInt(request.getParameter("seatCount")));
        train.setRateId(Integer.parseInt(request.getParameter("rate")));
        return train;
    }
}
