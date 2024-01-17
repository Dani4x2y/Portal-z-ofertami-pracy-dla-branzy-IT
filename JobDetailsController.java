package com.example.itjobportal.controllers;

import com.example.itjobportal.models.JobOffer;
import com.example.itjobportal.services.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class JobDetailsController {

    private final JobOfferService jobOfferService;

    @Autowired
    public JobDetailsController(JobOfferService jobOfferService) {
        this.jobOfferService = jobOfferService;
    }

    @GetMapping("/details/{id}")
    public String getJobDetails(@PathVariable Long id, Model model) {
        JobOffer jobOffer = jobOfferService.getJobOfferById(id);

        if (jobOffer == null) {
            // Obsłuż sytuację, gdy ogłoszenie o podanym id nie istnieje
            return "redirect:/index";
        }

        model.addAttribute("jobOffer", jobOffer);
        return "frontend/job_details";
    }
}

