package ru.tsystems.tsproject.sbb.transferObjects;

/**
 * Represents client entity of Station.
 * @author Daria Nikiforova
 */
public class StationTO {
    private String name;

    public StationTO() {
    }

    public StationTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
