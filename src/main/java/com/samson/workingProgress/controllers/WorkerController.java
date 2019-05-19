package com.samson.workingProgress.controllers;

import com.samson.workingProgress.models.Repos.WorkerRepo;
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
}
