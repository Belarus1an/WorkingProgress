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

    default int showProgress(List<Orders> ordersList, List<Toner> tonerList){

        int sumPoints = 0;

        for (Orders valueOrders: ordersList){
            for (Toner valueToner: tonerList){
                if (valueToner.getTonerName().equals(valueOrders.getTonerName())){
                    sumPoints += valueToner.getPoints();
                }
            }
        }
        return sumPoints;
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
            if (!value.getDate().before(date1) && !value.getDate().after(date2)){
                newOrdersList.add(value);
            }
        }
        return newOrdersList;
    }

    default List<Orders> findOrdersByDate(List<Orders> all, Date date){

        List<Orders> ordersList = new ArrayList<>();

        for (Orders orderValue: all){
            if (!orderValue.getDate().before(date) && !orderValue.getDate().after(date)){
                ordersList.add(orderValue);
            }
        }
        return ordersList;
    }
}
