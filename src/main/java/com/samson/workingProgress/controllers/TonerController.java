package com.samson.workingProgress.controllers;

import com.samson.workingProgress.models.Repos.TonerRepo;
import com.samson.workingProgress.models.Toner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class TonerController {

    @Autowired
    TonerRepo tonerRepo;

    @RequestMapping("/toners")
    public String showToners(ModelMap modelMap){

        modelMap.put("tonerList", tonerRepo.findAll());

        return "toners";
    }

    @PostMapping("/toners")
    public String addToner(@RequestParam String tonerName, @RequestParam int points, ModelMap modelMap){

        if (!tonerRepo.checkTonerName(tonerName, tonerRepo.findAll())){
            String infoNegative = "Nieprawidłowe wprowadzenie danych!";
            modelMap.put("infoNegative", infoNegative);
            modelMap.put("tonerList", tonerRepo.findAll());
        }else {
            tonerRepo.save(new Toner(tonerName, points));
            String infoPositive = "Dodano do bazy";
            modelMap.put("infoPositive", infoPositive);
            modelMap.put("tonerList", tonerRepo.findAll());
        }
        return "toners";
    }

    @GetMapping("/editToner/{tonerID}")
    public String showUpdateForm(@PathVariable int tonerID, ModelMap modelMap){

        modelMap.put("toner", tonerRepo.findById(tonerID).get());

        return "updateToner";
    }

    @PostMapping("/updateToner/{tonerID}")
    public String updateToner(@PathVariable int tonerID, @RequestParam String tonerName, @RequestParam int points,
                              ModelMap modelMap){

        Toner toner = tonerRepo.findById(tonerID).get();
        toner.setTonerName(tonerName);
        toner.setPoints(points);
        tonerRepo.save(toner);

        modelMap.put("tonerList", tonerRepo.findAll());

        return "toners";
    }

    @GetMapping("/deleteToner/{tonerID}")
    public String deleteToner(@PathVariable int tonerID){

        tonerRepo.delete(tonerRepo.findById(tonerID).get());

        return "redirect:/toners";
    }
}
