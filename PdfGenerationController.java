package com.example.itjobportal.controllers;

import com.example.itjobportal.services.PdfGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PdfGenerationController {

    private final PdfGenerationService pdfGenerationService;

    @Autowired
    public PdfGenerationController(PdfGenerationService pdfGenerationService) {
        this.pdfGenerationService = pdfGenerationService;
    }

    @PostMapping("/generate-pdf")
    public String generatePdf(@RequestParam("jobOfferId") Long jobOfferId) {
        pdfGenerationService.generatePdf(jobOfferId);
        // Obsłuż przekierowanie lub dodaj informację o pomyślnym wygenerowaniu PDF
        return "redirect:/index";
    }
}
