package com.samson.workingProgress.controllers;

import com.samson.workingProgress.models.Orders;
import com.samson.workingProgress.models.Repos.OrderRepo;
import com.samson.workingProgress.models.Repos.TonerRepo;
import com.samson.workingProgress.models.Repos.WorkerRepo;
import com.samson.workingProgress.models.Toner;
import com.samson.workingProgress.models.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class OrderController {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private TonerRepo tonerRepo;

    @Autowired
    private WorkerRepo workerRepo;

    @RequestMapping("/orders")
    public String showOrders(ModelMap modelMap){

        Iterable<Orders> orderList = orderRepo.findAll();
        Iterable<Toner> tonerList = tonerRepo.findAll();
        Iterable<Worker> workerList = workerRepo.findAll();

        modelMap.put("orderList", orderList);
        modelMap.put("tonerList", tonerList);
        modelMap.put("workerList", workerList);

        return "orders";
    }

    @PostMapping("/orders")
    public String createOrder(@RequestParam int workerID,
                              @RequestParam int tonerID,
                              @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                              ModelMap modelMap){

        Worker worker = workerRepo.findById(workerID).get();
        Toner toner = tonerRepo.findById(tonerID).get();

        Orders orders = new Orders(worker.getWorkerID(), worker.getWorkerName(), toner.getTonerName(), date);
        orderRepo.save(orders);
        Iterable<Orders> ordersList = orderRepo.findAll();

        modelMap.put("ordersList", ordersList);

        return "redirect:/orders";
    }
}
