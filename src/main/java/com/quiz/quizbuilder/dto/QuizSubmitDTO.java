package com.quiz.quizbuilder.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class QuizSubmitDTO {
    private Long questionId;
    private Set<Long> responses;
}
