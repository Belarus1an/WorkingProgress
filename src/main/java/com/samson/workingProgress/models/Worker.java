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
    private String nameWorker;
    private int pesel;

    public Worker() {
    }

    public Worker(String name, int pesel) {
        this.nameWorker = name;
        this.pesel = pesel;
    }

    public int getWorkerID() {
        return workerID;
    }

    public String getNameWorker() {
        return nameWorker;
    }

    public int getPesel() {
        return pesel;
    }
}
