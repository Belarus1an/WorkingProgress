package com.samson.workingProgress.controllers;

import com.samson.workingProgress.models.Orders;
import com.samson.workingProgress.models.Repos.OrderRepo;
import com.samson.workingProgress.models.Repos.TonerRepo;
import com.samson.workingProgress.models.Repos.WorkerRepo;
import com.samson.workingProgress.models.Toner;
import com.samson.workingProgress.models.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private TonerRepo tonerRepo;

    @Autowired
    private WorkerRepo workerRepo;

    @RequestMapping("/")
    public String showOrders(ModelMap modelMap){

        List<Orders> orderList = orderRepo.findAll();
        List<Toner> tonerList = tonerRepo.findAll();
        List<Worker> workerList = workerRepo.findAll();

        modelMap.put("orderList", orderList);
        modelMap.put("tonerList", tonerList);
        modelMap.put("workerList", workerList);

        return "orders";
    }

}
