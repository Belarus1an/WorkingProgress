package com.samson.workingProgress.controllers;

import com.samson.workingProgress.models.Repos.OrderRepo;
import com.samson.workingProgress.models.Repos.WorkerRepo;
import com.samson.workingProgress.models.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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
    public  String addWorker(@RequestParam String workerName, @RequestParam String pesel, ModelMap modelMap){

        if (!workerRepo.checkWorkerPesel(pesel, workerRepo.findAll())){
            modelMap.put("infoNegative", "Nieprawidłowe wprowadzenie danych!");
            modelMap.put("workerList", workerRepo.findAll());
        } else {
            workerRepo.save(new Worker(workerName, pesel));
            modelMap.put("infoPositive", "Dodano do bazy");
            modelMap.put("workerList", workerRepo.findAll());
        }
        return "workers";
    }


    @GetMapping("/editWorker/{workerID}")
    public String showUpdateForm(@PathVariable("workerID") int workerID, ModelMap modelMap) {

        modelMap.put("worker", workerRepo.findById(workerID).get());

        return "updateWorker";
    }

    @PostMapping("/updateWorker/{workerID}")
    public String updateWorker(@PathVariable("workerID") int workerID, @RequestParam String workerName, @RequestParam String pesel, ModelMap modelMap) {

        Worker worker = workerRepo.findById(workerID).get();
        worker.setWorkerName(workerName);
        worker.setPesel(pesel);
        workerRepo.save(worker);

        modelMap.put("workerList", workerRepo.findAll());

        return "workers";
    }

    @GetMapping("/deleteWorker/{workerID}")
    public String deleteWorker(@PathVariable int workerID){

        workerRepo.delete(workerRepo.findById(workerID).get());

        return "redirect:/workers";
    }

    @RequestMapping("/progressDetails/{workerID}")
    public String showProgressDetails(@PathVariable int workerID, ModelMap modelMap){

        Worker worker = workerRepo.findById(workerID).get();

        modelMap.put("ordersListProgress", orderRepo.showListProgress(orderRepo.findAll(), workerID));
        modelMap.put("workerName", worker.getWorkerName());
        modelMap.put("workerID", workerID);

        return "progressDetails";
    }
}
