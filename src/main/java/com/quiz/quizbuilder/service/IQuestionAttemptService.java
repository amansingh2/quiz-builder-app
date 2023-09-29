package com.quiz.quizbuilder.service;

import com.quiz.quizbuilder.entity.QuestionAttempt;

import java.util.List;

public interface IQuestionAttemptService {

    QuestionAttempt create(QuestionAttempt questionAttempt);
    QuestionAttempt getById(Long id);

    QuestionAttempt findByUserQuestionQuiz(Long userId, Long quesId, Long quizId);

    List<QuestionAttempt> saveAll(List<QuestionAttempt> questionAttempts);

    List<QuestionAttempt> findByUserAndQuiz(Long userId, Long quizId);

}
