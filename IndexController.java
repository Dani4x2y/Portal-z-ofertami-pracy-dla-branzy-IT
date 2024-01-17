package com.example.itjobportal.controllers;

import com.example.itjobportal.repositories.JobOfferRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.itjobportal.models.JobOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;


import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private JobOfferRepository jobOfferRepository;

    @GetMapping("/index")
    public String index(Model model) {
        List<JobOffer> jobOffers = jobOfferRepository.findAll();
        model.addAttribute("jobOffers", jobOffers);
        return "frontend/index";
    }

    @GetMapping("/")
    public String ind(Model model) {
        return index(model);
    }

}
