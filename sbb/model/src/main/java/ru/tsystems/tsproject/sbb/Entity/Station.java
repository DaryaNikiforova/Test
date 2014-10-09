package ru.tsystems.tsproject.sbb.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 04.10.14.
 */
@Entity
public class Station {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "station")
    private List<TimetableRecord> timetable;

    public Station() {
        timetable = new ArrayList<TimetableRecord>();
    }

    public Station(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public List<TimetableRecord> getTimetables(){
        return timetable;
    }

    public void setName(String name) {
        this.name=name;
    }

    public void addTimetable(TimetableRecord timetable) {
        if (!getTimetables().contains(timetable)) {
            getTimetables().add(timetable);
        }
    }

    public int getId() {
        return id;
    }
}
