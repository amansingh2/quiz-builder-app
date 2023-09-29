package com.quiz.quizbuilder.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OptionBodyCorrectDto {
    private String body;
    private Boolean isCorrect;
}
