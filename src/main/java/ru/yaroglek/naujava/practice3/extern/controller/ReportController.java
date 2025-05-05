package ru.yaroglek.naujava.practice3.extern.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yaroglek.naujava.practice3.app.service.ReportService;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping
    public String getReportContentById(@RequestParam Long id) {
        return reportService.getReportContent(id);
    }

    @PostMapping
    public Long createAndFormReport() {
        Long id = reportService.createReport();
        reportService.formReport(id);
        return id;
    }
}
