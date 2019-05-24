package com.samson.workingProgress.models;

import javax.persistence.*;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderID;

    private String workerName;
    private String tonerName;
    private String date;

    public Orders() {
    }

    public Orders(String workerName, String tonerName, String date) {
        this.workerName = workerName;
        this.tonerName = tonerName;
        this.date = date;
    }

    public int getOrderID() {
        return orderID;
    }

    public String getWorkerName() {
        return workerName;
    }

    public String getTonerName() {
        return tonerName;
    }

    public String getDate() {
        return date;
    }
}
