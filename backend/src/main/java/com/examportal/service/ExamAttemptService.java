package com.examportal.service;

import com.examportal.model.ExamAttempt;
import java.util.Map;

public interface ExamAttemptService {
    ExamAttempt submitAttempt(Long userId, Long examId, Map<Long, Integer> answers);
    ExamAttempt getReport(Long userId, Long examId);
}