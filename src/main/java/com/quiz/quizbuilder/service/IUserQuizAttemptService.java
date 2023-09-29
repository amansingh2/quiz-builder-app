package com.quiz.quizbuilder.service;

import com.quiz.quizbuilder.dto.UserQuizAttemptDTO;
import com.quiz.quizbuilder.entity.UserQuizAttempt;

import java.util.List;

public interface IUserQuizAttemptService {
    UserQuizAttempt save(UserQuizAttempt userQuizAttempt);

    boolean hasUserAttemptedQuiz(Long userId, Long quizId);

    List<UserQuizAttemptDTO> getAttemptedUsers(Long quizId);

}
