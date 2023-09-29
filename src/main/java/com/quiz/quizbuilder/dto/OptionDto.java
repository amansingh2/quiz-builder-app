package com.quiz.quizbuilder.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OptionDto {
    private long id;
    private String body;
}
