package ru.tsystems.tsproject.sbb.services.helpers;

import ru.tsystems.tsproject.sbb.database.entity.Trip;

/**
 * Created by apple on 21.10.14.
 */
public class TicketHelper {
    private TicketHelper(){}

    public static String getTrainInfo(Trip trip) {
        return trip.getTrain().getId() + trip.getTrain().getName() + RouteHelper.getRouteName(trip.getRoute());
    }

}
