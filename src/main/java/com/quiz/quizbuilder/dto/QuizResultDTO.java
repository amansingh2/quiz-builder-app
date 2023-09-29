package com.quiz.quizbuilder.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuizResultDTO {
    private Long quizId;
    private Integer numberOfQuestions;
    private Double score;
    private Integer attemptedQuestions;
    private Integer correctResponses;
}
