package com.examportal.service.Impl;

import com.examportal.model.*;
import com.examportal.repository.*;
import com.examportal.service.ExamAttemptService;
import com.examportal.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ExamAttemptServiceImpl implements ExamAttemptService {

    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExamAttemptRepository examAttemptRepository;
    @Autowired
    private ReportService reportService;

    @Override
    public ExamAttempt submitAttempt(Long userId, Long examId, Map<Long, Integer> answers) {
        User user = userRepository.findById(userId).orElseThrow();
        Exam exam = examRepository.findById(examId).orElseThrow();

        int totalMarks = 0;
        int score = 0;

        for (Question q : exam.getQuestions()) {
            totalMarks += q.getMarks(); // Sum all question marks

            Integer selectedIndex = answers.get(q.getQuestionId());
            if (selectedIndex != null && selectedIndex >= 0 && selectedIndex < q.getOptions().size()) {
                String selectedOption = q.getOptions().get(selectedIndex);
                if (selectedOption.equals(q.getCorrectAnswer())) {
                    score += q.getMarks(); // Add marks for correct answer
                }
            }
        }

        ExamAttempt attempt = new ExamAttempt();
        attempt.setUser(user);
        attempt.setExam(exam);
        attempt.setAnswers(answers);
        attempt.setScore(score);
        examAttemptRepository.save(attempt);

        // Save report
        Report report = new Report();
        report.setUserID(userId);
        report.setExamID(examId);
        report.setTotalMarks(totalMarks);
        report.setScore(score);
        // Optionally set performanceMetric
        reportService.saveReport(report);

        return attempt;
    }

    @Override
    public ExamAttempt getReport(Long userId, Long examId) {
        return examAttemptRepository.findByUser_UserIdAndExam_ExamId(userId, examId).orElseThrow();
    }
}