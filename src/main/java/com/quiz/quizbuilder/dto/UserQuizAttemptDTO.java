package com.quiz.quizbuilder.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserQuizAttemptDTO {
    private String username;
    private Double score;
    private Long quizId;
    private Long userId;
    private Integer totalQuestions;
}
