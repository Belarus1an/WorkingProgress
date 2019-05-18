package com.samson.workingProgress.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class WorkerController {

    @RequestMapping("/")
    public String index(ModelMap modelMap){

        return "index";
    }
}
