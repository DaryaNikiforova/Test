package ru.tsystems.tsproject.sbb.Entity;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by apple on 04.10.14.
 */
@Entity
@Table(name = "Timetable_record", uniqueConstraints = {@UniqueConstraint(columnNames = {"train_number", "station_id"})})
public class TimetableRecord {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    private Station station;

    @ManyToOne
    private Train train;

    private Time time;

    public TimetableRecord() {
    }

    public TimetableRecord(Train train, Station station, Time time) {
        this.train=train;
        this.station=station;
        this.time=time;
    }


    public Train getTrain() {
        return train;
    }

    public Time getTime() {
        return time;
    }

    public void setStation(Station station) {
        this.station=station;
    }

    public void setTrain(Train train) {
        this.train=train;
    }

    public void setTime(Time time) {
        this.time=time;
    }
}
