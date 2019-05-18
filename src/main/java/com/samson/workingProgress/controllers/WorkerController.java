package com.samson.workingProgress.controllers;

import com.samson.workingProgress.models.Repos.WorkerRepo;
import com.samson.workingProgress.models.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class WorkerController {

    @Autowired
    WorkerRepo workerRepo;

    @RequestMapping("/")
    public String index(ModelMap modelMap){

        List<Worker> workerList = workerRepo.ALL_WORKER;
        modelMap.put("workers", workerList);

        return "index";
    }
}
