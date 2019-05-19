package com.samson.workingProgress.controllers;

import com.samson.workingProgress.models.Repos.TonerRepo;
import com.samson.workingProgress.models.Toner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TonerController {

    @Autowired
    TonerRepo tonerRepo;

    @RequestMapping("/toners")
    public String showToners(ModelMap modelMap){

        List<Toner> tonerList = tonerRepo.findAll();
        modelMap.put("tonerList", tonerList);

        return "toners";
    }

    @RequestMapping("/addToner")
    public String addToner(){
        return "addToner";
    }

    @PostMapping("/addToner")
    public String createToner(@RequestParam String tonerName, @RequestParam int points, ModelMap modelMap){

        Toner newToner = new Toner(tonerName, points);
        tonerRepo.save(newToner);
        List<Toner> tonerList = tonerRepo.findAll();
        modelMap.put("tonerList", tonerList);

        return "toners";
    }
}
