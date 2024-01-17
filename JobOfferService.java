package com.example.itjobportal.services;

import com.example.itjobportal.models.JobOffer;
import com.example.itjobportal.repositories.JobOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class JobOfferService {

    private final JobOfferRepository jobOfferRepository;

    @Autowired
    public JobOfferService(JobOfferRepository jobOfferRepository) {
        this.jobOfferRepository = jobOfferRepository;
    }

    public List<JobOffer> getAllJobOffers() {
        return jobOfferRepository.findAll();
    }

    public JobOffer getJobOfferById(Long id) {
        return jobOfferRepository.findById(id).orElse(null);
    }

    public List<JobOffer> getJobOffersByProvince(String province) {
        return jobOfferRepository.findByProvince(province);
    }
    public List<JobOffer> getJobOffersBySalaryRange(BigDecimal minSalary, BigDecimal maxSalary) {
        return jobOfferRepository.findBySalaryBetween(minSalary, maxSalary);
    }

}
