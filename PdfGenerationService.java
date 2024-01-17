package com.example.itjobportal.services;

import com.example.itjobportal.models.JobOffer;
import com.example.itjobportal.repositories.JobOfferRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Optional;

@Service
public class PdfGenerationService {

    private final JobOfferRepository jobOfferRepository;

    @Autowired
    public PdfGenerationService(JobOfferRepository jobOfferRepository) {
        this.jobOfferRepository = jobOfferRepository;
    }

    public void generatePdf(Long jobOfferId) {
        // Pobierz ofertę pracy na podstawie identyfikatora
        Optional<JobOffer> jobOfferOptional = jobOfferRepository.findById(jobOfferId);

        // Sprawdź, czy oferta pracy istnieje
        if (jobOfferOptional.isPresent()) {
            JobOffer jobOffer = jobOfferOptional.get();
            // Tutaj dodaj logikę generowania PDF na podstawie obiektu JobOffer
            generatePdfFromJobOffer(jobOffer);
        } else {
            // Obsłuż przypadek, gdy oferta pracy o podanym ID nie istnieje
            throw new IllegalArgumentException("Oferta pracy o ID " + jobOfferId + " nie istnieje.");
        }
    }

    private void generatePdfFromJobOffer(JobOffer jobOffer) {
        // Tutaj dodaj logikę generowania PDF na podstawie obiektu JobOffer
        Document document = new Document();

        try {
            // Uzyskaj ścieżkę do folderu "Pobrane" w systemie użytkownika
            String userDownloadsFolder = System.getProperty("user.home") + "/Pobrane/";

            // Sprawdź, czy folder istnieje, jeśli nie, utwórz go
            File downloadsFolder = new File(userDownloadsFolder);
            if (!downloadsFolder.exists()) {
                downloadsFolder.mkdirs();
            }

            // Określ ścieżkę do pliku PDF
            String pdfFilePath = userDownloadsFolder + jobOffer.getTitle() + "_Offer.pdf";

            // Utwórz nowy dokument PDF
            PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));
            document.open();

            // Dodaj treść do dokumentu
            document.add(new Paragraph("Job Title: " + jobOffer.getTitle()));
            document.add(new Paragraph("Description: " + jobOffer.getDescription()));
            document.add(new Paragraph("Salary: " + jobOffer.getSalary() + " zł"));
            document.add(new Paragraph("Employment Type: " + jobOffer.getEmploymentType()));
            document.add(new Paragraph("Province: " + jobOffer.getProvince()));
            document.add(new Paragraph("Location: " + jobOffer.getLocation()));

            // Zamknij dokument
            document.close();

            // Dodaj dodatkową logikę, np. informacja o pomyślnym wygenerowaniu PDF
            System.out.println("PDF wygenerowany pomyślnie: " + pdfFilePath);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
