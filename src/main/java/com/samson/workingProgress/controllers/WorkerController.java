package com.samson.workingProgress.controllers;

import com.samson.workingProgress.models.Orders;
import com.samson.workingProgress.models.Repos.OrderRepo;
import com.samson.workingProgress.models.Repos.WorkerRepo;
import com.samson.workingProgress.models.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@SessionAttributes("workerID")
public class WorkerController {

    @Autowired
    private WorkerRepo workerRepo;

    @Autowired
    private OrderRepo orderRepo;

    @RequestMapping("/workers")
    public String showWorkers(ModelMap modelMap){

        List<Worker> workerList = workerRepo.findAll();
        modelMap.put("workerList", workerList);

        return "workers";
    }

    @PostMapping("/workers")
    public  String createWorker(@RequestParam String workerName, @RequestParam String pesel, ModelMap modelMap){

        boolean checkPesel = workerRepo.checkWorkerPesel(pesel, workerRepo.findAll());

        if (!checkPesel){
            String infoNegative = "Nieprawidłowe wprowadzenie danych!";
            modelMap.put("infoNegative", infoNegative);
            modelMap.put("workerList", workerRepo.findAll());
        } else {
            Worker newWorker = new Worker(workerName, pesel);
            workerRepo.save(newWorker);
            String infoPositive = "Dodano do bazy";

            modelMap.put("infoPositive", infoPositive);
            modelMap.put("workerList", workerRepo.findAll());
        }
        return "workers";
    }


    @GetMapping("/edit/{workerID}")
    public String showUpdateForm(@PathVariable("workerID") int workerID, ModelMap modelMap) {
        Worker worker = workerRepo.findById(workerID).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + workerID));
        modelMap.put("worker", worker);
        return "updateWorker";
    }

    @PostMapping("/updateWorker/{workerID}")
    public String updateUser(@PathVariable("workerID") int workerID, @RequestParam String workerName, @RequestParam String pesel, ModelMap modelMap) {

        Worker worker = workerRepo.findById(workerID).get();
        worker.setWorkerName(workerName);
        worker.setPesel(pesel);

        workerRepo.save(worker);
        modelMap.put("workerList", workerRepo.findAll());
        return "workers";
    }

    @GetMapping("/deleteWorker/{workerID}")
    public String deleteWorker(@PathVariable int workerID){

        Worker worker = workerRepo.findById(workerID).get();
        workerRepo.delete(worker);

        return "redirect:/workers";
    }

    @RequestMapping("/progressDetails/{workerID}")
    public String showProgressDetails(@PathVariable int workerID, ModelMap modelMap){

        List<Orders> ordersList = orderRepo.findAll();
        List<Orders> ordersListProgress = orderRepo.showListProgress(ordersList, workerID);

        Worker worker = workerRepo.findById(workerID).get();

        modelMap.put("ordersListProgress", ordersListProgress);
        modelMap.put("workerName", worker.getWorkerName());
        modelMap.put("workerID", workerID);

        return "progressDetails";
    }
}
