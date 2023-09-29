package com.quiz.quizbuilder.service;

import com.quiz.quizbuilder.dto.DetailedResultDTO;

public interface IDetailedResultService {
    DetailedResultDTO generate(Long quizId, Long userId);
}
