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

    public Worker(String name, int pesel) {
        this.name = name;
        this.pesel = pesel;
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
}
