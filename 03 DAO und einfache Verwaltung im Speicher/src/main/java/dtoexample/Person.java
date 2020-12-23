package dtoexample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Person {
    private int id;
    private String name;
    private String address;
    private List<Duration> durations = new ArrayList<>();

    public Person() {}

    public Person(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Duration> getDurations() {
        return durations;
    }
    public void addDuration(Date from, Date to) {
        durations.add(new Duration(from, to));
    }
}
