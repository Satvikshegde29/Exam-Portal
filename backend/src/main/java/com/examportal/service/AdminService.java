package com.examportal.service;

import  java.util.List;

import com.examportal.model.Question;


public interface AdminService {
    Question addQuestion(Question question);
    Question updateQuestion(Long questionId, Question updatedQuestion);
    void deleteQuestion(Long questionId);
    List<Question> getAllQuestions();
    List<Question> getQuestionsByCategory(String category);
    List<Question> getQuestionsByDifficulty(String difficulty);
}