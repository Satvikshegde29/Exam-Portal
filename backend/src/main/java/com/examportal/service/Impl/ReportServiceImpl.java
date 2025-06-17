package com.examportal.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.examportal.model.Report;
import com.examportal.repository.ReportRepository;
import com.examportal.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    /**
     * Fetch all reports.
     * @return List of reports.
     */
    @Override
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    /**
     * Fetch reports based on criteria (e.g., examID or userID).
     * @param examID The exam ID to filter reports.
     * @param userID The user ID to filter reports.
     * @return List of filtered reports.
     */
    @Override
    public List<Report> getReportsByCriteria(Long examID, Long userID) {
        List<Report> reports = reportRepository.findAll();
        if (examID != null && userID != null) {
            return reports.stream()
                    .filter(report -> report.getExamID().equals(examID) && report.getUserID().equals(userID))
                    .collect(Collectors.toList());
        } else if (examID != null) {
            return reports.stream()
                    .filter(report -> report.getExamID().equals(examID))
                    .collect(Collectors.toList());
        } else if (userID != null) {
            return reports.stream()
                    .filter(report -> report.getUserID().equals(userID))
                    .collect(Collectors.toList());
        } else {
            return reports;
        }
    }

    /**
     * Save a new report.
     * @param report The report to save.
     * @return The saved report.
     */
    @Override
    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }
}