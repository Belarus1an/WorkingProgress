package com.samson.workingProgress.controllers;

import com.samson.workingProgress.models.Orders;
import com.samson.workingProgress.models.Repos.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class ProgressController {

    @Autowired
    OrderRepo orderRepo;

    @RequestMapping("/progressDetails/filter")
    public String showProgress(
                            @RequestParam int workerID,
                            @RequestParam("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date1,
                            @RequestParam("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date2,
                            ModelMap modelMap){

        List<Orders> ordersList = orderRepo.findAll();
        List<Orders> ordersWorkerList = orderRepo.showListProgress(ordersList, workerID);
        List<Orders> ordersDateList = orderRepo.findByOrdersDate(ordersWorkerList, date1, date2);

        modelMap.put("ordersListProgress", ordersDateList);

        return "progressDetails";
    }
}
