package ru.tsystems.tsproject.sbb.services.helpers;

import ru.tsystems.tsproject.sbb.database.entity.Route;
import ru.tsystems.tsproject.sbb.database.entity.RouteEntry;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by apple on 19.10.14.
 */
public class RouteHelper {

    private RouteHelper(){}

    public static String getRouteDistance(Route route) {
        List<RouteEntry> list = route.getRouteEntries();
        Collections.sort(list, new Comparator<RouteEntry>() {
            public int compare(RouteEntry o1, RouteEntry o2) {
                return o1.getSeqNumber() - o2.getSeqNumber();
            }
        });

        return Integer.toString(list.get(list.size() - 1).getDistance());
    }

    public static String getRouteTime(Route route) {
        List<RouteEntry> list = route.getRouteEntries();
        Collections.sort(list, new Comparator<RouteEntry>() {
            public int compare(RouteEntry o1, RouteEntry o2) {
                return o1.getSeqNumber() - o2.getSeqNumber();
            }
        });
        return list.get(list.size() - 1).getHour() + " ч. " + list.get(list.size() - 1).getMinute() + " м.";
    }

    public static String getRouteName (Route route) {
        List<RouteEntry> list = route.getRouteEntries();
        Collections.sort(list, new Comparator<RouteEntry>() {
            public int compare(RouteEntry o1, RouteEntry o2) {
                return o1.getSeqNumber() - o2.getSeqNumber();
            }
        });
        return list.get(0).getStation().getName() + " &#8594; " + list.get(list.size() - 1).getStation().getName();
    }

    public static Date getArrivalDate(Route route, Date departureDate) {
        List<RouteEntry> list = route.getRouteEntries();
        Collections.sort(list, new Comparator<RouteEntry>() {
            public int compare(RouteEntry o1, RouteEntry o2) {
                return o1.getSeqNumber() - o2.getSeqNumber();
            }
        });
        return TimeHelper.addHours(departureDate, list.get(list.size() - 1).getHour(), list.get(list.size() - 1).getMinute());
    }

}
