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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@SessionAttributes("workerID")
public class ProgressController {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    WorkerRepo workerRepo;

    @Autowired
    TonerRepo tonerRepo;

    @RequestMapping("/progressDetails/filter")
    public String showProgress(
            @ModelAttribute("workerID") int workerID,
            @RequestParam("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date1,
            @RequestParam("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date2,
            ModelMap modelMap){

        List<Toner> tonerList = tonerRepo.findAll();
        List<Orders> ordersList = orderRepo.findAll();
        List<Orders> ordersWorkerList = orderRepo.showListProgress(ordersList, workerID);
        List<Orders> ordersDateList = orderRepo.findByOrdersDate(ordersWorkerList, date1, date2);

        Worker worker = workerRepo.findById(workerID).get();

        int sumPoints = orderRepo.showProgress(ordersDateList, tonerList);
        float sumSalary = (float) (sumPoints * 0.5);

        modelMap.put("sumPoints", sumPoints);
        modelMap.put("sumSalary", sumSalary);
        modelMap.put("ordersListProgress", ordersDateList);
        modelMap.put("workerName", worker.getWorkerName());

        return "progressDetails";
    }
}
