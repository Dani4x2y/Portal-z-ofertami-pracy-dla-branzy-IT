package com.example.itjobportal.controllers;

import com.example.itjobportal.models.JobOffer;
import com.example.itjobportal.services.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

public class JobOfferController {
    private final JobOfferService jobOfferService;

    @Autowired
    public JobOfferController(JobOfferService jobOfferService) {
        this.jobOfferService = jobOfferService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        List<JobOffer> jobOffers = jobOfferService.getAllJobOffers();
        System.out.println("Liczba ogłoszeń: " + jobOffers.size());
        model.addAttribute("jobOffers", jobOffers);
        return "frontend/index";
    }

    @GetMapping("/search")
    public String searchJobOffers(
            @RequestParam(required = false) String province,
            @RequestParam(required = false) BigDecimal minSalary,
            @RequestParam(required = false) BigDecimal maxSalary,
            Model model) {

        List<JobOffer> jobOffers;

        if (province != null) {
            jobOffers = jobOfferService.getJobOffersByProvince(province);
        } else if (minSalary != null && maxSalary != null) {
            jobOffers = jobOfferService.getJobOffersBySalaryRange(minSalary, maxSalary);
        } else {
            jobOffers = jobOfferService.getAllJobOffers();
        }

        model.addAttribute("jobOffers", jobOffers);
        return "frontend/index";
    }
}
