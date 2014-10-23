package ru.tsystems.tsproject.sbb.validation;

import ru.tsystems.tsproject.sbb.transferObjects.RouteTO;
import ru.tsystems.tsproject.sbb.transferObjects.StationTO;
import ru.tsystems.tsproject.sbb.transferObjects.TrainTO;
import ru.tsystems.tsproject.sbb.validation.impl.RouteValidatorImpl;
import ru.tsystems.tsproject.sbb.validation.impl.StationValidatorImpl;
import ru.tsystems.tsproject.sbb.validation.impl.TrainValidatorImpl;

public class ValidationManager {
    public static Validator getValidator(Object obj) {
        if (StationTO.class.equals(obj.getClass())) {
            return new StationValidatorImpl((StationTO) obj);
        }
        if (TrainTO.class.equals(obj.getClass())) {
            return new TrainValidatorImpl((TrainTO) obj);
        }
        if (RouteTO.class.equals(obj.getClass())) {
            return new RouteValidatorImpl((RouteTO) obj);
        }
        else return null;
    }
}
