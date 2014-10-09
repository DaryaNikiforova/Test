package ru.tsystems.tsproject.sbb.Entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by apple on 04.10.14.
 */
@Entity
public class Train {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int number;

    private int seatsCount;


    @OneToMany(mappedBy = "train")
    private List<TimetableRecord> timetable;

    public Train(){
    }

    public Train(int number, int seatsCount) {
        this();
        this.number=number;
        this.seatsCount=seatsCount;
    }


    public int getTrainNumber() {
        return number;
    }

    public int getSeatsCount() {
        return seatsCount;
    }


    public void setNumber(int number) {
        this.number=number;
    }


    public void setTimetable(List<TimetableRecord> timetable) {
        this.timetable = timetable;
    }

    public void setSeatsCount (int count) {
        this.seatsCount=count;
    }
}
