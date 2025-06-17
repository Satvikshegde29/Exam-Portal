package com.examportal.service;

import com.examportal.model.Exam;

import java.util.List;

public interface ExamService {
    Exam createExam(Exam exam);
    Exam updateExam(Long examId, Exam updatedExam);
    void deleteExam(Long examId);
    List<Exam> getAllExams();
    Exam getExamById(Long examId);
}