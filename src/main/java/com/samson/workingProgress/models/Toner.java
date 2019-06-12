package com.samson.workingProgress.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Toner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int tonerID;
    private String tonerName;
    private int points;

    public Toner() {
    }

    public Toner(String tonerName, int points) {
        this.tonerName = tonerName;
        this.points = points;
    }

    public int getTonerID() {
        return tonerID;
    }

    public String getTonerName() {
        return tonerName;
    }

    public int getPoints() {
        return points;
    }

    public void setTonerName(String tonerName) {
        this.tonerName = tonerName;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
