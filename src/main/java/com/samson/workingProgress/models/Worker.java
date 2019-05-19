package com.samson.workingProgress.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int workerID;
    private String workerName;
    private String pesel;

    public Worker() {
    }

    public Worker(String name, String pesel) {
        this.workerName = name;
        this.pesel = pesel;
    }

    public int getWorkerID() {
        return workerID;
    }

    public String getWorkerName() {
        return workerName;
    }

    public String getPesel() {
        return pesel;
    }
}
