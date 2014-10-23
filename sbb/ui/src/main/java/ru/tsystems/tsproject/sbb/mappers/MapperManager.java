package ru.tsystems.tsproject.sbb.mappers;

import ru.tsystems.tsproject.sbb.mappers.impl.RouteMapperImpl;
import ru.tsystems.tsproject.sbb.mappers.impl.StationMapperImpl;
import ru.tsystems.tsproject.sbb.mappers.impl.TrainMapperImpl;
import ru.tsystems.tsproject.sbb.transferObjects.RouteTO;
import ru.tsystems.tsproject.sbb.transferObjects.StationTO;
import ru.tsystems.tsproject.sbb.transferObjects.TrainTO;

/**
 * Created by Rin on 22.10.2014.
 */
public class MapperManager {
    public static Mapper getMapper(Class TOType) {
        if (StationTO.class.equals(TOType)) {
            return new StationMapperImpl();
        }
        if (TrainTO.class.equals(TOType)) {
            return new TrainMapperImpl();
        }
        if (RouteTO.class.equals(TOType)) {
            return new RouteMapperImpl();
        }
        return null;
    }
}
