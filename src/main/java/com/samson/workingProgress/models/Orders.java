package com.samson.workingProgress.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderID;
    private int workerID;
    private String workerName;
    private String tonerName;
    private Date date;

    public Orders() {
    }

    public Orders(int workerID, String workerName, String tonerName, Date date) {
        this.workerID = workerID;
        this.workerName = workerName;
        this.tonerName = tonerName;
        this.date = date;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getWorkerID() {
        return workerID;
    }

    public String getWorkerName() {
        return workerName;
    }

    public String getTonerName() {
        return tonerName;
    }

    public Date getDate() {
        return date;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public void setTonerName(String tonerName) {
        this.tonerName = tonerName;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
