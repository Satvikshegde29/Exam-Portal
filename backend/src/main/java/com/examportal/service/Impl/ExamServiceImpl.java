package com.examportal.service.Impl;

import com.examportal.model.Exam;
import com.examportal.repository.ExamRepository;
import com.examportal.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Override
    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
    }

    @Override
    public Exam updateExam(Long examId, Exam updatedExam) {
        Exam existingExam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found with ID: " + examId));
        existingExam.setTitle(updatedExam.getTitle());
        existingExam.setDescription(updatedExam.getDescription());
        existingExam.setDuration(updatedExam.getDuration());
        existingExam.setTotalMarks(updatedExam.getTotalMarks());
        return examRepository.save(existingExam);
    }

    @Override
    public void deleteExam(Long examId) {
        if (!examRepository.existsById(examId)) {
            throw new RuntimeException("Exam not found with ID: " + examId);
        }
        examRepository.deleteById(examId);
    }

    @Override
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    @Override
    public Exam getExamById(Long examId) {
        return examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found with ID: " + examId));
    }
}
