package com.quiz.quizbuilder.dto;

import lombok.Data;

@Data
public class UpdateQuizDto {
    private String title;
    private String description;
    private Integer numberOfQuestions;
    private Long timeInSeconds;
    private Boolean isPublished;
}
