package com.samson.workingProgress.models;

import javax.persistence.*;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderID;

    private int workerID;
    private int tonerID;
    private String date;

    public Orders() {
    }

    public Orders(int workerID, int tonerID, String date) {
        this.workerID = workerID;
        this.tonerID = tonerID;
        this.date = date;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getWorkerID() {
        return workerID;
    }

    public int getTonerID() {
        return tonerID;
    }

    public String getDate() {
        return date;
    }
}
