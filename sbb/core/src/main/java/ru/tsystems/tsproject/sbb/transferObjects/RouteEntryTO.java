package ru.tsystems.tsproject.sbb.transferObjects;

/**
 * Represents client entity of RouteEntry.
 * @author Daria Nikiforova
 */
public class RouteEntryTO {
    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getSeqNumber() {
        return seqNumber;
    }

    public void setSeqNumber(int seqNumber) {
        this.seqNumber = seqNumber;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }


    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    private String stationName;
    private int seqNumber;
    private int distance;
    private int hour;
    private int minute;
}
