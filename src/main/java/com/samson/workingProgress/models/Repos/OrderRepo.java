package com.samson.workingProgress.models.Repos;

import com.samson.workingProgress.models.Orders;
import com.samson.workingProgress.models.Toner;
import com.samson.workingProgress.models.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepo extends JpaRepository<Orders, Integer> {

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
}
