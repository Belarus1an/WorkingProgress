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

    @RequestMapping("/")
    public String index(ModelMap modelMap){

        return "index";
    }

    @GetMapping("/addWorker")
    public String addWorker(ModelMap modelMap){

        return "addWorker";
    }

    @PostMapping("addWorker")
    public  String createWorker(@RequestBody String nameWorker, @RequestParam int pesel, ModelMap modelMap){

        Worker newWorker = new Worker(nameWorker, pesel);
        workerRepo.save(newWorker);
        List<Worker> workerList = workerRepo.findAll();

        modelMap.put("workerList", workerList);

        return "index";
    }
}
