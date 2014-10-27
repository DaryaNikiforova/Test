package ru.tsystems.tsproject.sbb.transferObjects;

/**
 * Represents client entity of Train.
 * @author Daria Nikiforova
 */
public class TrainTO {
    private int number;
    private String name;
    private int seatCount;
    private int rateId;
    private String rateName;

    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getSeatCount() { return seatCount; }
    public void setSeatCount(int seatCount) { this.seatCount = seatCount; }
    public int getRateId() { return rateId; }
    public void setRateId(int rateId) { this.rateId = rateId; }
    public String getRateName() { return rateName; }
    public void setRateName(String rateName) { this.rateName = rateName; }
}
