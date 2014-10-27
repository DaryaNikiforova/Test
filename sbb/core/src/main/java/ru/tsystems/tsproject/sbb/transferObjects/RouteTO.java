package ru.tsystems.tsproject.sbb.transferObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents client entity of Route.
 * @author Daria Nikiforova
 */
public class RouteTO {

    public RouteTO() {
        routeEntries = new ArrayList<RouteEntryTO>();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<RouteEntryTO> getRouteEntries() {
        return routeEntries;
    }

    //public void setRouteEntries(List<RouteEntryTO> routeEntries) {
      //  this.routeEntries = routeEntries;
    //}

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    private int number;
    private List<RouteEntryTO> routeEntries;
    private String route;
    private String time;
    private String distance;

}
