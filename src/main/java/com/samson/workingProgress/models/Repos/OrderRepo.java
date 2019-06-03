package com.samson.workingProgress.models.Repos;

import com.samson.workingProgress.models.Orders;
import com.samson.workingProgress.models.Toner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Repository
public interface OrderRepo extends JpaRepository<Orders, Integer> {

    List<Orders> findByDateBetween(Date from, Date to);

    default int showProgress(List<Orders> ordersList, List<Toner> tonerList, int workerID){

        int sumPoints = 0;

        for (Orders valueOrders: ordersList){
            if (valueOrders.getWorkerID() == workerID){
                for (Toner valueToner: tonerList){
                    if (valueToner.getTonerName().equals(valueOrders.getTonerName())){
                        sumPoints += valueToner.getPoints();
                    }
                }
            }
        }

        return sumPoints;
    }

    default String showNameWorker(List<Orders> ordersList, int workerID){

        String workerName = "";

        for (Orders ordersValue: ordersList){
            if (ordersValue.getWorkerID() == workerID){
                workerName = ordersValue.getWorkerName();
            }
        }

        return workerName;
    }

    default List<Orders> showListProgress(List<Orders> ordersList, int workerID){

        List<Orders> newOrderList = new ArrayList<>();

        for(Orders value: ordersList){
            if (value.getWorkerID() == workerID){
                newOrderList.add(value);
            }
        }
        return newOrderList;
    }

    default List<Orders> findByOrdersDate(List<Orders> ordersWorkerList, Date date1, Date date2){

        List<Orders> newOrdersList = new ArrayList<>();

        for (Orders value: ordersWorkerList){
            if (value.getDate().after(date1) && value.getDate().before(date2)){
                newOrdersList.add(value);
            }
        }
        return newOrdersList;
    }
}
