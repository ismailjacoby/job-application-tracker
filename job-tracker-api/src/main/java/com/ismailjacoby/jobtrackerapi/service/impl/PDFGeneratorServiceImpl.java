package com.ismailjacoby.jobtrackerapi.service.impl;

import com.ismailjacoby.jobtrackerapi.exception.NotFoundException;
import com.ismailjacoby.jobtrackerapi.model.entity.Job;
import com.ismailjacoby.jobtrackerapi.model.entity.User;
import com.ismailjacoby.jobtrackerapi.repository.JobRepository;
import com.ismailjacoby.jobtrackerapi.repository.UserRepository;
import com.ismailjacoby.jobtrackerapi.service.PDFGeneratorService;
import jakarta.servlet.http.HttpServletResponse;
import org.openpdf.text.*;
import org.openpdf.text.pdf.PdfPCell;
import org.openpdf.text.pdf.PdfPTable;
import org.openpdf.text.pdf.PdfWriter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.awt.Color;

import java.io.IOException;
import java.util.List;

@Service
public class PDFGeneratorServiceImpl implements PDFGeneratorService {
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public PDFGeneratorServiceImpl(JobRepository jobRepository, UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void export(HttpServletResponse response) throws IOException {
        // Create Pdf
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        // Title
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);
        Paragraph title = new Paragraph("Job Applications Report", fontTitle);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        // Get User & Fetch Jobs
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = principal.toString();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Authenticated user not found."));
        List<Job> jobs = jobRepository.findByUserIdOrderByDateAppliedDesc(user.getId());


        // User Info and stats
        document.add(new Paragraph(""));
        document.add(new Paragraph("Generated for: " + user.getFirstName() + " " + user.getLastName()));
        document.add(new Paragraph("Email: " + user.getEmail()));
        document.add(new Paragraph("Total applications: " + jobs.size()));
        document.add(new Paragraph(""));
        document.add(new Paragraph(""));

        // Table setup
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10);
        table.setWidths(new float[]{3.5f, 2.5f, 2f, 2f, 2f});

        // Table header
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        addTableHeader(table, headerFont, "Title", "Company", "Location", "Status", "Applied");

        // Table data
        Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 10);
        for (Job job : jobs) {
            table.addCell(new PdfPCell(new Phrase(job.getTitle(), cellFont)));
            table.addCell(new PdfPCell(new Phrase(job.getCompanyName(), cellFont)));
            table.addCell(new PdfPCell(new Phrase(job.getLocation(), cellFont)));
            table.addCell(new PdfPCell(new Phrase(job.getStatus().toString(), cellFont)));
            table.addCell(new PdfPCell(new Phrase(job.getDateApplied() != null ? job.getDateApplied().toString() : "", cellFont)));
        }

        document.add(table);
        document.close();

    }

    private void addTableHeader(PdfPTable table, Font font, String... headers) {
        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header, font));
            cell.setBackgroundColor(Color.LIGHT_GRAY);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(5);
            table.addCell(cell);
        }
    }
}
