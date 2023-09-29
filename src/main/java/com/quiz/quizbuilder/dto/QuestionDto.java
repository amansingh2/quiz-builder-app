package com.quiz.quizbuilder.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionDto {
    private long id;
    private String body;

}
