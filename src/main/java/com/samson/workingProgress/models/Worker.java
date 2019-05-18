package com.samson.workingProgress.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Worker {

    @Id
    @GeneratedValue
    private int workerID;
    private String name;
    private int pesel;
    private int orderID;

    public Worker(String name, int pesel, int orderID) {
        this.name = name;
        this.pesel = pesel;
        this.orderID = orderID;
    }

    public int getWorkerID() {
        return workerID;
    }

    public String getName() {
        return name;
    }

    public int getPesel() {
        return pesel;
    }

    public int getOrderID() {
        return orderID;
    }
}
