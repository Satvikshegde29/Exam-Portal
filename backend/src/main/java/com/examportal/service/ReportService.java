package com.examportal.service;

import java.util.List;

import com.examportal.model.Report;

public interface ReportService {

    /**
     * Fetch all reports.
     * @return List of reports.
     */
    List<Report> getAllReports();

    /**
     * Fetch reports based on criteria (e.g., examID or userID).
     * @param examID The exam ID to filter reports.
     * @param userID The user ID to filter reports.
     * @return List of filtered reports.
     */
    List<Report> getReportsByCriteria(Long examID, Long userID);

    /**
     * Save a new report.
     * @param report The report to save.
     * @return The saved report.
     */
    Report saveReport(Report report);
}