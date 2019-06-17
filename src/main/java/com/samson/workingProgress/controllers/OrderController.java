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
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private TonerRepo tonerRepo;

    @Autowired
    private WorkerRepo workerRepo;

    @GetMapping("/")
    public String index(ModelMap modelMap){

        modelMap.put("orderList", orderRepo.findAll());
        modelMap.put("tonerList", tonerRepo.findAll());
        modelMap.put("workerList", workerRepo.findAll());

        return "orders";
    }

    @GetMapping("/orders")
    public String showOrders(ModelMap modelMap){

        modelMap.put("orderList", orderRepo.findAll());
        modelMap.put("tonerList", tonerRepo.findAll());
        modelMap.put("workerList", workerRepo.findAll());

        return "orders";
    }

    @PostMapping("/orders")
    public String addOrder(@RequestParam int workerID,
                           @RequestParam int tonerID,
                           @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                           ModelMap modelMap){

        Worker worker = workerRepo.findById(workerID).get();
        Toner toner = tonerRepo.findById(tonerID).get();
        Orders orders = new Orders(worker.getWorkerID(), worker.getWorkerName(), toner.getTonerName(), date);

        orderRepo.save(orders);

        modelMap.put("infoPositive", "Dodano do bazy");
        modelMap.put("orderList", orderRepo.findAll());
        modelMap.put("tonerList", tonerRepo.findAll());
        modelMap.put("workerList", workerRepo.findAll());

        return "orders";
    }

    @GetMapping("/editOrder/{orderID}")
    public String showUpdateForm(@PathVariable int orderID, ModelMap modelMap){

        modelMap.put("orders", orderRepo.findById(orderID).get());
        modelMap.put("tonerList", tonerRepo.findAll());

        return "updateOrder";
    }

    @PostMapping("/updateOrder/{orderID}")
    public String updateWorker(@PathVariable("orderID") int orderID,
                               @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                               @RequestParam int tonerID, ModelMap modelMap) {

        Orders order = orderRepo.findById(orderID).get();
        order.setDate(date);
        order.setTonerName(tonerRepo.findById(tonerID).get().getTonerName());
        orderRepo.save(order);

        modelMap.put("orderList", orderRepo.findAll());

        return "orders";
    }

    @RequestMapping("/deleteOrder/{orderID}")
    public String deleteOrder(@PathVariable int orderID, ModelMap modelMap){

        orderRepo.delete(orderRepo.findById(orderID).get());
        modelMap.put("orderList", orderRepo.findAll());
        modelMap.put("workerList", workerRepo.findAll());
        modelMap.put("tonerList", tonerRepo.findAll());

        return "redirect:/orders";
    }

    @RequestMapping("/orders/filter")
    public String findOrders(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date, ModelMap modelMap){

        List<Orders> ordersList = orderRepo.findOrdersByDate(orderRepo.findAll(), date);
        if (!ordersList.isEmpty()){
            modelMap.put("orderList", ordersList);
        } else {
            modelMap.put("infoNegative", "Nic nie znaleziono!");
        }


        return "orders";
    }
}
