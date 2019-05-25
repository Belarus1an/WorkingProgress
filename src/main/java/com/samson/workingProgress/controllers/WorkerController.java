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
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class WorkerController {

    @Autowired
    private WorkerRepo workerRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private TonerRepo tonerRepo;

    @RequestMapping("/workers")
    public String showWorkers(ModelMap modelMap){

        List<Worker> workerList = workerRepo.findAll();
        modelMap.put("workerList", workerList);

        return "workers";
    }

    @PostMapping("/workers")
    public  String createWorker(@RequestParam String workerName, @RequestParam String pesel, ModelMap modelMap){

        Worker newWorker = new Worker(workerName, pesel);
        workerRepo.save(newWorker);
        List<Worker> workerList = workerRepo.findAll();

        modelMap.put("workerList", workerList);

        return "workers";
    }

    @GetMapping("/deleteWorker/{workerID}")
    public String deleteWorker(@PathVariable int workerID){

        Worker worker = workerRepo.findById(workerID).get();
        workerRepo.delete(worker);

        return "redirect:/workers";
    }

    @GetMapping("/workerProgress/{workerID}")
    public String showProgress(@PathVariable int workerID, ModelMap modelMap){

        List<Orders> ordersList = orderRepo.findAll();
        List<Toner> tonerList = tonerRepo.findAll();

        int sumPoints = orderRepo.showProgress(ordersList, tonerList, workerID);
        String workerName = orderRepo.showNameWorker(ordersList, workerID);

        modelMap.put("workerName", workerName);
        modelMap.put("sumPoints", sumPoints);

        return "progress";
    }
}
